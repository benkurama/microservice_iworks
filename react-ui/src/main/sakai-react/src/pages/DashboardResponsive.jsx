
import React from 'react';

import '../assets/css/dashboard.css';

import MapFrag from '../pages/fragments/MapFrag';
import ChartFrag from './fragments/ChartFrag';
import CoverFlow from '../pages/fragments/CoverFlow';

import {line001, line002, line003, line004} from '../assets/json/lineChart';
import {bar001, bar002, bar003, bar004} from '../assets/json/barChart';
import {pie001, pie002, pie003, pie004, pie005} from '../assets/json/pieChart';

const DashboardResponsive = () => {

    return (
        
        <div style={{pCarouselItemsContent: ''}} className="bg_samp">

          <div id="header" 
                style={{width:'100%', height:'90px', position:'fixed', textAlign: 'center', color:'white'
                
            }}
           className="bg_header">
                    <div className="grid p-fluid" style={{height:'100%'}} >
                        <div className="col-12 lg:col-4" >

                            
                        </div>
                        <div className="col-12 lg:col-4" >
                        <table style={{height:'100%', width:'100%'}}>
                                <tr>
                                    <td valign='middle' align='center'>
                                       <h2> INTERACTIVE MICROSERVICE DASHBOARD </h2>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div className="col-12 lg:col-4" >
                            
                        </div>
                    </div>
          </div>

            <div className="layout-main-container">
                <div className="layout-main">

                <div className="grid p-fluid">
                    <div className="col-12 lg:col-4" >
                       
                        <ChartFrag param1={line001} param2={line002} param3={line003} param4={line004}              
                        playInterval={5000}
                        />
                        <br></br>
                           {/*
                            <ChartFrag param1={pie005} param2={pie002} param3={pie003} param4={pie004}
                                playInterval={9000}
                            /> 
                           */}
                            
                          <CoverFlow param1={bar001} param2={bar002} param3={bar003} param4={bar004} />
                            

                    </div>
                    <div className="col-12 lg:col-4">
                        

                        <MapFrag />
                        
                    </div>
                        <div className="col-12 lg:col-4">

                            <ChartFrag param1={bar001} param2={bar002} param3={bar003} param4={bar004}
                                playInterval={7000}
                            />
                            <br></br>

                            <CoverFlow param1={pie005} param2={pie002} param3={pie003} param4={pie004} />

                        </div>

                    <div className="col-12 lg:col-12" >
                        
                    </div>
                </div>
                    
                </div>
            </div>
        </div>
    )

}

export default DashboardResponsive;