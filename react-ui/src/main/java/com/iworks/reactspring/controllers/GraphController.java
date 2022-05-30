package com.iworks.reactspring.controllers;

import com.iworks.reactspring.models.GraphsModel;
import com.iworks.reactspring.models.LineGraph;
import com.iworks.reactspring.services.InstallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/graph")
public class GraphController {
    @Autowired
    InstallService installService;

    String[] bgColorList = new String[] {"#FFCE56", "#64B5F6" ,"#00008B","#6A5ACD", "#800000", "#800080", "#808000",
            "#BDB76B", "#DC143C","#42A5F5","#66BB6A","#FFA726","#81C784" ,"#FF6384","#36A2EB"};

    @GetMapping("/show")
    public GraphsModel mainShow(){

        List<Map> res = installService.showGraph001();
        int count = res.size();
        //  divider ----------
        String[] lbl = res.stream().map(t-> t.get("dateStr").toString()).toArray(String[]::new);

        int[] countData = res.stream().mapToInt(t-> (int)t.get("count")).toArray();
        //  divider ----------
        GraphsModel gm = new GraphsModel();
        gm.setLabels(lbl);

        List<GraphsModel.datasets> datasetsList = new ArrayList<>();
        datasetsList.add(new GraphsModel.datasets(countData, bgColorList));
        gm.setDatasets(datasetsList);

        return gm;
    }

    @GetMapping("/show002")
    public LineGraph showGrap002(){
        List<Map> res = installService.showGraph002();

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
        List<Map> res = installService.showReceiptGraph001();
        int count = res.size();
        //  divider ----------
        String[] lbl = res.stream().map(t-> t.get("dateStr").toString()).toArray(String[]::new);

        int[] countData = res.stream().mapToInt(t-> (int)t.get("count")).toArray();

        String[] bgColor = new String[count];
        for(int i=0; i < count; i++){
            bgColor[i] = bgColorList[i];
        }
        //  divider ----------
        GraphsModel gm = new GraphsModel();
        gm.setLabels(lbl);

        List<GraphsModel.datasets> datasetsList = new ArrayList<>();
        datasetsList.add(new GraphsModel.datasets(countData, bgColor));
        gm.setDatasets(datasetsList);

        return gm;
    }

    @GetMapping("/showReceiptGraph002")
    public LineGraph showReceiptGraph002(){
        List<Map> res = installService.showReceiptGraph002();

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
        List<Map> res = installService.selectTransHistoryGraph001();

        int count = res.size();

       String[] lbl = res.stream().map(x-> x.get("processType").toString()).toArray(String[]::new);

       int[] countData = res.stream().mapToInt(x-> (int)x.get("count")).toArray();

        String[] bgColor = new String[count];
        for(int i=0; i < count; i++){
            bgColor[i] = bgColorList[i];
        }
        //  divider ----------
        GraphsModel gm = new GraphsModel();
        gm.setLabels(lbl);

        List<GraphsModel.datasets> datasetsList = new ArrayList<>();
        datasetsList.add(new GraphsModel.datasets(countData, bgColor));
        gm.setDatasets(datasetsList);

        return gm;
    }

    @GetMapping("/selectDashbordCount")
    public Map selectDashbordCount(){
        Map res = installService.selectDashbordCount();

        return res;
    }

    @GetMapping("/selectCurrentDataInstallOrder")
    public List<Map> selectCurrentDataInstallOrder(){
        List<Map> res = installService.selectCurrentDataInstallOrder();

        return res;
    }

    @GetMapping("/selecAreaGroupByState")
    public List<Map> selecAreaGroupByState(){

        List<Map> res = installService.selecAreaGroupByState();

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
