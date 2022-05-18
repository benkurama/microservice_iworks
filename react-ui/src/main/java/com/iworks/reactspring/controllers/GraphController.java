package com.iworks.reactspring.controllers;

import com.iworks.reactspring.models.GraphsModel;
import com.iworks.reactspring.services.InstallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/graph")
public class GraphController {
    @Autowired
    InstallService installService;

    String[] bgColorList = new String[] {"#42A5F5","#66BB6A","#FFA726","#81C784","#FF6384","#36A2EB","#FFCE56", "#64B5F6"};

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
}
