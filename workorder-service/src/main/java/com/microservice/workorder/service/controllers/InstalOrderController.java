package com.microservice.workorder.service.controllers;

import com.microservice.workorder.service.model.LineGraph;
import com.microservice.workorder.service.repository.InstallOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/installorder/inst")
@Slf4j
public class InstalOrderController {

    @Autowired
    private InstallOrderRepository installOrderRepository;

    String[] bgColorList = new String[] {"#FFCE56", "#64B5F6" ,"#00008B","#6A5ACD", "#800000", "#800080", "#808000",
            "#BDB76B", "#DC143C","#42A5F5","#66BB6A","#FFA726","#81C784" ,"#FF6384","#36A2EB"};

    @GetMapping("/showAll")
    public List<Map> showall(){

        List<Map> res = installOrderRepository.selectAll();

        return res;
    }

    @GetMapping("/showTableColumns")
    public List<Map> showTableColumns(){
        return installOrderRepository.selectTableColumns();
    }

    @GetMapping("/showGraph001")
    public List<Map> showGraph001(){
        return installOrderRepository.selectGraph001();
    }

    @GetMapping("/showGraph002")
    public LineGraph showGraph002(){

        List<Map> res =  installOrderRepository.selectGraph002();

        //>>>> Get all months by data list and save to dateStr array
        Map<String, Long> mapRegsGroup = res.stream().collect(Collectors.groupingBy(map -> map.get("subArea").toString(),
                Collectors.counting() ));

        Object[] obj = helper.me.GetAllMonths(res, mapRegsGroup, "subArea");

        String[] dateStr = (String[]) obj[0];
        int count = (int) obj[1];
        //<<<<
        //-------------------------
        List<Map> mapList = helper.me.PopulateDataByMonth(mapRegsGroup, res, count, dateStr, "subArea");
        //  ====================================================================
        LineGraph lg = new LineGraph();


        lg.setLabels(dateStr);
        List<LineGraph.datasets> datasetsList = new ArrayList<>();

        int iii=0;
        for(Map map : mapList){
            String subArea = map.get("label").toString();
            int[] datas = (int[])map.get(subArea);
            datasetsList.add(new LineGraph.datasets(subArea, datas, bgColorList[iii], bgColorList[iii]));
            iii++;
        }
        lg.setDatasets(datasetsList);

        return lg;

    }

    @GetMapping("/showReceiptGraph001")
    public List<Map> showReceiptGraph001(){
        return installOrderRepository.selectReceiptGraph001();
    }

    @GetMapping("/showReceiptGraph002")
    public List<Map> showReceiptGraph002(){
        return installOrderRepository.selectReceiptGraph002();
    }

    @GetMapping("/selectTransHistoryGraph001")
    public List<Map> selectTransHistoryGraph001(){
        return installOrderRepository.selectTransHistoryGraph001();
    }

    @GetMapping("/selectDashbordCount")
    public Map selectDashbordCount(){
        List<Map> userCount = installOrderRepository.selectUserCount();
        List<Map> installCount = installOrderRepository.selectInstallCount();
        List<Map> transCount = installOrderRepository.selectTransCount();
        List<Map> shipCount = installOrderRepository.selectShipCount();

        Map map = new HashMap();

        map.put("UserCount", userCount);
        map.put("InstallCount", installCount);
        map.put("TransCount", transCount);
        map.put("ShipCount", shipCount);

        return map;
    }

    @GetMapping("/selectCurrentDataInstallOrder")
    public List<Map> selectCurrentDataInstallOrder(){

        return installOrderRepository.selectCurrentDataInstallOrder();
    }

    @GetMapping("/selecAreaGroupByState")
    public List<Map> selecAreaGroupByState(){

        return installOrderRepository.selecAreaGroupByState();
    }

    public enum helper{
        me;

        public Object[] GetAllMonths(List<Map> res, Map<String, Long> mapRegsGroup, String type) {

            final String[] selectedArea = {""};
            final int[] selectedCount = {0};

            mapRegsGroup.forEach((s, aLong) ->
                    {
                        if(selectedCount[0] < aLong){
                            selectedCount[0] = Math.toIntExact(aLong);
                            selectedArea[0] = s;
                        }
                    }
            );

            final String selArea = selectedArea[0];
            //--
            List<Map> mapFillFilter = res.stream().filter(map -> map.get(type).toString().equals(selArea))
                    .collect(Collectors.toList());
            //--
            int count = mapFillFilter.size();
            String[] dateStr = new String[count];
            int i = 0;

            for (Map entry : mapFillFilter) {
                dateStr[i] = entry.get("dateStr").toString();
                i++;
            }

            Object[] ret = new Object[2];

            ret[0] = dateStr;
            ret[1] = count;

            return ret;
        }

        public List<Map> PopulateDataByMonth(Map<String, Long> mapRegsGroup, List<Map> res, int count, String[] dateStr, String type) {

            List<Map> mapList = new ArrayList<>();

            for (Map.Entry<String, Long> entry : mapRegsGroup.entrySet()) {
                String k = entry.getKey();

                List<Map> mapFill001 = res.stream().filter(map -> map.get(type).toString().equals(k))
                        .collect(Collectors.toList());

                int[] data = new int[count];

                for(int ii=0; ii < count; ii++){
                    String date = dateStr[ii];
                    List<Map> monthData =  mapFill001.stream().filter(map -> map.get("dateStr").toString().equals(date))
                            .collect(Collectors.toList());

                    if(monthData.size() != 0){
                        data[ii] = Integer.parseInt( monthData.get(0).get("count").toString() );
                    } else {
                        data[ii] = 0;
                    }
                }

                Map map = new HashMap();
                //
                map.put("label", k);
                map.put(k, data);
                mapList.add(map);
            }

            return mapList;
        }
    }
}
