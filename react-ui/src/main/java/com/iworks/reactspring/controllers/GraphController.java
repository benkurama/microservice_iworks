package com.iworks.reactspring.controllers;

import com.iworks.reactspring.models.GraphsModel;
import com.iworks.reactspring.models.LineGraph;
import com.iworks.reactspring.services.InstallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/graph")
public class GraphController {
    @Autowired
    InstallService installService;

    String[] bgColorList = new String[] {"#FFCE56", "#64B5F6"
    ,"#00008B","#6A5ACD", "#800000", "#800080", "#808000",
            "#BDB76B", "#DC143C","#42A5F5","#66BB6A","#FFA726","#81C784"
    ,"#FF6384","#36A2EB"};

    @GetMapping("/show")
    public GraphsModel mainShow(){

        List<Map> res = installService.showGraph001();
        int count = res.size();
        //  divider ----------
        String[] lbl = new String[count];
        for(int i=0; i < count; i++){
            lbl[i] = res.get(i).get("dateStr").toString();
        }

        int[] countData = new int[count];
        //  divider ----------
        for(int i=0; i < count; i++){
            countData[i] = Integer.parseInt(res.get(i).get("count").toString());
        }

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

    @GetMapping("/show002")
    public LineGraph showGrap002(){
        List<Map> res = installService.showGraph002();

        //>>>> Get all months by data list and save to dateStr array
        Map<String, Long> mapRegsGroup = res.stream()
                .collect(Collectors.groupingBy(map -> map.get("subArea").toString(),
                        Collectors.counting() ));

        String selectedArea = "";
        int selectedCount = 0;

        for (Map.Entry<String, Long> entry : mapRegsGroup.entrySet()) {
            String k = entry.getKey();
            int v = Integer.parseInt(entry.getValue()+"");

            if(selectedCount < v){
                selectedCount = v;
                selectedArea = k;
            }
        }

        final String selArea = selectedArea;
        //--
        List<Map> mapFillFilter = res.stream().filter(map -> map.get("subArea").toString().equals(selArea))
                .collect(Collectors.toList());
        //--
        int count = mapFillFilter.size();
        String[] dateStr = new String[count];
        int i = 0;

        for (Map entry : mapFillFilter) {
            dateStr[i] = entry.get("dateStr").toString();
            i++;
        }
        //<<<<
        //-------------------------
        List<Map> mapList = new ArrayList<>();

        for (Map.Entry<String, Long> entry : mapRegsGroup.entrySet()) {
            String k = entry.getKey();

            List<Map> mapFill001 = res.stream().filter(map -> map.get("subArea").toString().equals(k))
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
            map.put(k,data);
            mapList.add(map);
        }
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
        String[] lbl = new String[count];
        for(int i=0; i < count; i++){
            lbl[i] = res.get(i).get("dateStr").toString();
        }

        int[] countData = new int[count];
        //  divider ----------
        for(int i=0; i < count; i++){
            countData[i] = Integer.parseInt(res.get(i).get("count").toString());
        }

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

        String selectedArea = "";
        int selectedCount = 0;

        for (Map.Entry<String, Long> entry : mapRegsGroup.entrySet()) {
            String k = entry.getKey();
            int v = Integer.parseInt(entry.getValue()+"");

            if(selectedCount < v){
                selectedCount = v;
                selectedArea = k;
            }
        }

        final String selArea = selectedArea;
        //--
        List<Map> mapFillFilter = res.stream().filter(map -> map.get("internalInstructionType").toString().equals(selArea))
                .collect(Collectors.toList());
        //--
        int count = mapFillFilter.size();
        String[] dateStr = new String[count];
        int i = 0;

        for (Map entry : mapFillFilter) {
            dateStr[i] = entry.get("dateStr").toString();
            i++;
        }
        //<<<<
        //-------------------------
        List<Map> mapList = new ArrayList<>();

        for (Map.Entry<String, Long> entry : mapRegsGroup.entrySet()) {
            String k = entry.getKey();

            List<Map> mapFill001 = res.stream().filter(map -> map.get("internalInstructionType").toString().equals(k))
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
            map.put(k,data);
            mapList.add(map);
        }
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
        //  divider ----------
        String[] lbl = new String[count];
        for(int i=0; i < count; i++){
            lbl[i] = res.get(i).get("processType").toString();
        }

        int[] countData = new int[count];
        //  divider ----------
        for(int i=0; i < count; i++){
            countData[i] = Integer.parseInt(res.get(i).get("count").toString());
        }

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
}
