package com.microservice.systemservice.controllers;

import com.microservice.systemservice.models.MenuClass;
import com.microservice.systemservice.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/menu")
public class SystemController {

    @Autowired
    SystemRepository systemRepository;

    @GetMapping("/show")
    public String showString(){
        return "SYSTEM-SERVICE IS IN THE HOUSE";
    }

    @GetMapping("/showMenuResources")
    public List<MenuClass> showMenuResources(String role){

        String roleTrim = role.substring(5,role.length());
        List<Map> res = systemRepository.findByRole(roleTrim);

        boolean isSubmenu = true;

        List<MenuClass> mac = new ArrayList<>();
        MenuClass mc = new MenuClass();
        List<MenuClass> item1 = new ArrayList<>();

        //---------------->>>
        String newlevel = "1";
        String oldllevel = "1";
        String levelStatus = "none";
        int type1 = 0;
        int type2 = 0;

        // ---
        mc = new MenuClass();
        mc.label = "Home";
        item1 = new ArrayList<>();
        item1.add(new MenuClass("Dashboard", "pi pi-fw pi-home", "input"));
        mc.items = item1;
        mac.add(mc);
        // ---
        for(int i = 0; i < res.size(); i++){
            Map map = res.get(i);
            type1 = Integer.parseInt(map.get("TYPE_").toString());

            try{
                Map map2 = res.get(i+1);
                type2 = Integer.parseInt(map2.get("TYPE_").toString());
            }catch (Exception ee){

                if(oldllevel.equals("2") && newlevel.equals("2")){
                    item1.add(new MenuClass(res.get(i).get("PAGES").toString(), "pi pi-fw pi-home", "/"));
                }
                type2 = 0;
            }

            if(type1 < type2 ){
                newlevel = type2+"";
                levelStatus = "up";
            }

            if(type1 == type2){
                levelStatus = "mid";
            }

            if(type1 > type2){
                newlevel = type2+"";
                levelStatus = "down";
            }

            if(levelStatus.equals("up")){
                mc = new MenuClass();
                mc.label = map.get("PAGES").toString();

                item1 = new ArrayList<>();

                oldllevel = newlevel;
                continue;
            }

            if(levelStatus.equals("mid")){

                if(oldllevel.equals("1") && newlevel.equals("1")){
                    mc = new MenuClass();
                    mc.label = map.get("PAGES").toString();
                    item1 = new ArrayList<>();
                    item1.add(new MenuClass(res.get(i).get("PAGES").toString(), "pi pi-fw pi-home", "/"));
                    mc.items = item1;
                    mac.add(mc);
                } else {
                    item1.add(new MenuClass(res.get(i).get("PAGES").toString(), "pi pi-fw pi-home", "/"));
                }


                continue;
            }

            if(levelStatus.equals("down")){

                boolean con = true;

                if(oldllevel.equals("2")  && newlevel.equals("1")){
                    item1.add(new MenuClass(res.get(i).get("PAGES").toString(), "pi pi-fw pi-home", "/"));
                }

                if(oldllevel.equals("1")  && newlevel.equals("0")){
                    mc = new MenuClass();
                    mc.label = map.get("PAGES").toString();
                    item1 = new ArrayList<>();
                    item1.add(new MenuClass(res.get(i).get("PAGES").toString(), "pi pi-fw pi-home", "/"));
                    mc.items = item1;
                    mac.add(mc);
                    con = false;
                }

                if(oldllevel.equals("1")  && newlevel.equals("1")) con = false;

                if(con) {
                    mc.items = item1;
                    mac.add(mc);
                    oldllevel = newlevel;
                }
                continue;
            }

            /*if(map.get("TYPE_").toString().equals("1")){

                if(level.equals("0") || level.equals("2")){
                    if(level.equals("2")) {
                        mc.items = item1;
                        mac.add(mc);
                    }

                }

                //level = map.get("TYPE_").toString();

                mc = new MenuClass();
                mc.label = map.get("PAGES").toString();

                if(level.equals("1") && map.get("TYPE_").toString().equals("1")){
                    mac.add(mc);
                }

                level = map.get("TYPE_").toString();

                continue;
            }

            if(map.get("TYPE_").toString().equals("2")){

                if(!level.equals(map.get("TYPE_").toString())){
                    level = map.get("TYPE_").toString();
                    item1 = new ArrayList<>();
                }

                item1.add(new MenuClass(res.get(i).get("PAGES").toString(), "pi pi-fw pi-home", "/"));

                continue;
            }*/
        }


        //----------------<<<
        return mac;
    }

    public void reserved(){
        /*MenuClass mc = new MenuClass();
        mc.label = "Home";
        //------------------
        List<MenuClass.items> itemsList = new ArrayList<>();
        itemsList.add(new MenuClass.items("Dashboard", "pi pi-fw pi-home", "/"));
        //------------------
        mc.items = itemsList;
        //------------------
        List<MenuClass> mac = new ArrayList<>();
        mac.add(mc);
        //------------------
        //------------------
        mc = new MenuClass();
        mc.label = roleTrim;

        itemsList = new ArrayList<>();

        itemsList = new ArrayList<>();
        itemsList.add(new MenuClass.items("Graphs", "pi pi-fw pi-chart-bar", "graphs"));
        itemsList.add(new MenuClass.items("Dashboard Responsive", "pi pi-fw pi-chart-bar", "/DashboardResponsive"));


        *//*for(Map map: res){
            itemsList.add(new MenuClass.items(map.get("PAGES").toString(), "pi pi-fw pi-chart-bar", "graphs"));
        }*//*

        mc.items = itemsList;

        mac.add(mc);*/



        // ===========================


        /*MenuClass mc = new MenuClass();
        // == >>
        mc.label = "Home";

        List<MenuClass> item111 = new ArrayList<>();
        item111.add(new MenuClass("Submenu 1.1.1", "pi pi-fw pi-home", "/"));
        item111.add(new MenuClass("Submenu 1.1.2", "pi pi-fw pi-home", "/"));

        List<MenuClass> item11 = new ArrayList<>();
        item11.add(new MenuClass("Submenu 1.1", "pi pi-fw pi-home", "/", item111));
        item11.add(new MenuClass("Submenu 1.2", "pi pi-fw pi-home", "/"));

        List<MenuClass> item1 = new ArrayList<>();
        item1.add(new MenuClass("Submenu 1", "pi pi-fw pi-home", "/", item11));

        //--

        mc.items = item1;
        // == <<
        List<MenuClass> mac = new ArrayList<>();
        mac.add(mc);*/
    }
}
