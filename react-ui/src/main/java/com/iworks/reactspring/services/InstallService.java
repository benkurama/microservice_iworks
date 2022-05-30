package com.iworks.reactspring.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name="install-service",url="http://localhost:8090", path="installorder/inst/")
@Service
public interface InstallService {

    @GetMapping(value = "/showAll")
    public List<Map> showAllAccounts();

    @GetMapping(value = "/showTableColumns")
    public List<Map> showTableColumns();

    @GetMapping("/showGraph001")
    public List<Map> showGraph001();

    @GetMapping("/showGraph002")
    public List<Map> showGraph002();


    @GetMapping("/showReceiptGraph001")
    public List<Map> showReceiptGraph001();

    @GetMapping("/showReceiptGraph002")
    public List<Map> showReceiptGraph002();

    @GetMapping("/selectTransHistoryGraph001")
    public List<Map> selectTransHistoryGraph001();

    @GetMapping("/selectDashbordCount")
    public Map selectDashbordCount();

    @GetMapping("/selectCurrentDataInstallOrder")
    public List<Map> selectCurrentDataInstallOrder();

    @GetMapping("/selecAreaGroupByState")
    public List<Map> selecAreaGroupByState();

}
