import { Chart } from 'primereact/chart';
import React, { useState, useEffect } from 'react';
import BaseService from '../../services/BaseService';


export default function LineGraph() {

    const [content, setContent] = useState("");

    const [lineGraph, setLineGraph] = useState("");

    useEffect(() => {
        
        BaseService.HttpGet("graph/show").then(
            (response) => {
                console.log('response');
                console.log(JSON.stringify(response.data));
                setContent(response.data);
            },
            (error) => {
                const _content =
                    (error.response && error.response.data) ||
                    error.message ||
                    error.toString();
                    console.log('error');
                    console.log(_content);
                setContent(_content);
            }
        );
        // --------------------
        BaseService.HttpGet("graph/show002").then(
            (response) => {
                //console.log('response');
                //console.log(JSON.stringify(response.data));
                setLineGraph(response.data);
            },
            (error) => {
                const _content =
                    (error.response && error.response.data) ||
                    error.message ||
                    error.toString();
                    console.log('error');
                    console.log(_content);
                    setLineGraph(_content);
            }
        );
        

        //console.log(JSON.stringify(chartData));
    }, []);

    const data = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [
            {
                label: 'First Dataset',
                data: [65, 59, 80, 81, 56, 55, 40],
                fill: false,
                borderColor: '#4bc0c0'
            }
        ]
    };

    const options = {
        plugins: {
            title: {
                display: true,
                text: 'Install Order Line Graph',
                font: {
                    size: 16
                }
            },
            legend: {
                position: 'bottom'
            }
        }
    };


/*
    const [chartData] = useState({
        labels: ['A', 'B', 'C'],
        datasets: [
            {
                data: [300, 50, 100],
                backgroundColor: [
                    "#42A5F5",
                    "#66BB6A",
                    "#FFA726"
                ],
                hoverBackgroundColor: [
                    "#64B5F6",
                    "#81C784",
                    "#FFB74D"
                ]
            }
        ]
    });
*/
    const [lightOptions] = useState({
        plugins: {
            legend: {
                labels: {
                    color: '#495057'
                }
            }
        }
    });




    const [chartData1] = useState({
        labels: ['A', 'B', 'C'],
        datasets: [
            {
                data: [300, 50, 100],
                backgroundColor: [
                    "#FF6384",
                    "#36A2EB",
                    "#FFCE56"
                ],
                hoverBackgroundColor: [
                    "#FF6384",
                    "#36A2EB",
                    "#FFCE56"
                ]
            }]
    });

    const [lightOptions1] = useState({
        plugins: {
            legend: {
                labels: {
                    color: '#495057'
                }
            }
        }
    });



    return(
        <div>

            <Chart type="line" data={lineGraph} options={options} height="130" />

            <br></br>
            <div className="row">
                <div class="col-md-6"  >

                    <p style={{ textAlign: "center" }}>INSTALL ORDER TICKET COUNT</p>
                    <Chart type="pie" data={content} options={lightOptions} />
                </div>
                <div class="col-md-6">
                    <p style={{ textAlign: "center" }}>INSTALL ORDER TICKET COUNT</p>
                    <Chart type="doughnut" data={content} options={lightOptions1} />
                </div>
            </div>


            
        </div>
    )
}