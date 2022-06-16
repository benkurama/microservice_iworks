package com.microservice.workorder.service.controllers;

import com.microservice.workorder.service.model.GraphsModel;
import com.microservice.workorder.service.model.LineGraph;
import com.microservice.workorder.service.repository.InstallOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
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
    public GraphsModel showGraph001(){
        //return installOrderRepository.selectGraph001();

        List<Map> res = installOrderRepository.selectGraph001();
        int count = res.size();
        //  divider ----------
        String[] lbl = res.stream().map(t-> t.get("dateStr").toString()).toArray(String[]::new);

        long[] countData = res.stream().mapToLong(t-> (long)t.get("count")).toArray();

        int[] intArray = Arrays.stream(countData).mapToInt(i -> (int) i).toArray();
        //  divider ----------
        GraphsModel gm = new GraphsModel();
        gm.setLabels(lbl);

        List<GraphsModel.datasets> datasetsList = new ArrayList<>();
        datasetsList.add(new GraphsModel.datasets(intArray, bgColorList));
        gm.setDatasets(datasetsList);

        return gm;
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
    public GraphsModel showReceiptGraph001(){
        //return installOrderRepository.selectReceiptGraph001();

        List<Map> res = installOrderRepository.selectReceiptGraph001();
        int count = res.size();
        //  divider ----------
        String[] lbl = res.stream().map(t-> t.get("dateStr").toString()).toArray(String[]::new);

        long[] countData = res.stream().mapToLong(t-> (long)t.get("count")).toArray();
        int[] intArray = Arrays.stream(countData).mapToInt(i -> (int) i).toArray();

        String[] bgColor = new String[count];
        for(int i=0; i < count; i++){
            bgColor[i] = bgColorList[i];
        }
        //  divider ----------
        GraphsModel gm = new GraphsModel();
        gm.setLabels(lbl);

        List<GraphsModel.datasets> datasetsList = new ArrayList<>();
        datasetsList.add(new GraphsModel.datasets(intArray, bgColor));
        gm.setDatasets(datasetsList);

        return gm;
    }

    @GetMapping("/showReceiptGraph002")
    public LineGraph showReceiptGraph002(){
        //return installOrderRepository.selectReceiptGraph002();

        List<Map> res = installOrderRepository.selectReceiptGraph002();

        //>>>> Get all months by data list and save to dateStr array
        Map<String, Long> mapRegsGroup = res.stream()
                .collect(Collectors.groupingBy(map -> map.get("internalInstructionType").toString(),
                        Collectors.counting() ));

        Object[] obj = helper.me.GetAllMonths(res, mapRegsGroup, "internalInstructionType");

        String[] dateStr = (String[]) obj[0];
        int count = (int) obj[1];
        //<<<<
        //-------------------------
        List<Map> mapList = helper.me.PopulateDataByMonth(mapRegsGroup, res, count, dateStr, "internalInstructionType");
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

    @GetMapping("/selectTransHistoryGraph001")
    public GraphsModel selectTransHistoryGraph001(){
        //return installOrderRepository.selectTransHistoryGraph001();

        List<Map> res = installOrderRepository.selectTransHistoryGraph001();

        int count = res.size();

        String[] lbl = res.stream().map(x-> x.get("processType").toString()).toArray(String[]::new);

        long[] countData = res.stream().mapToLong(x-> (long)x.get("count")).toArray();
        int[] intArray = Arrays.stream(countData).mapToInt(i -> (int) i).toArray();

        String[] bgColor = new String[count];
        for(int i=0; i < count; i++){
            bgColor[i] = bgColorList[i];
        }
        //  divider ----------
        GraphsModel gm = new GraphsModel();
        gm.setLabels(lbl);

        List<GraphsModel.datasets> datasetsList = new ArrayList<>();
        datasetsList.add(new GraphsModel.datasets(intArray, bgColor));
        gm.setDatasets(datasetsList);

        return gm;

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

        //return installOrderRepository.selecAreaGroupByState();

        List<Map> res = installOrderRepository.selecAreaGroupByState();

        /*List<String> list = res.stream().map(t -> t.get("total_count").toString()).collect(Collectors.toList());
        List<String[]> li = list.stream().map(t-> t.split(",")).collect(Collectors.toList());*/

        List<Map> rees = new ArrayList<>();

        for(Map map : res){
            int percentage = 0;
            String sample = map.get("total_count").toString();

            if(sample.contains("district")){
                try {
                    int val = sample.indexOf("district");
                    String vall = sample.substring(val);
                    int valll = vall.indexOf(",");
                    valll = valll == -1? 0 : valll;
                    String vallll = vall.substring(0, valll);

                    String distCount = vallll.split(":")[1];
                    int countDis = Integer.parseInt(distCount);

                    percentage = countDis * 100 / 150;
                }catch(Exception ee){
                    String error = ee.getMessage();
                }
            }
            String[] colors = {"bg-orange-500 h-full", "bg-cyan-500 h-full", "bg-teal-500 h-full", "bg-pink-500 h-full", "bg-purple-500 h-full"};
            Random rn = new Random();

            map.put("discount",percentage+"%");
            int randomNumber = (int) (Math.random()*(5))+1;
            map.put("color", colors[rn.nextInt(randomNumber)]);
            rees.add(map);
        }

        return rees;
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
