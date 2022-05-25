import React, { useEffect, useState } from 'react';
import { Chart } from 'primereact/chart';
import BaseService from '../../service/BaseService';


const ChartDemo = (props) => {
    const [lineOptions, setLineOptions] = useState(null)
    const [pieOptions, setPieOptions] = useState(null)
    const [barOptions, setBarOptions] = useState(null)

    const [lineGraph, setLineGraph] = useState(null)
    const [pieGraph, setPieGraph] = useState(null)
    const [doughnut, setDoughnut] = useState(null)
    const [bar, setBar] = useState(null)
    const [polar, setPolar] = useState(null)

    useEffect(() => {
        
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

                    console.log(_content.status);
                    if(_content.status == 401){
                        //window.location.reload();
                    }
                    

            }
        );
        // --------------------
        BaseService.HttpGet("graph/show").then(
            (response) => {
                console.log('response');
                console.log(JSON.stringify(response.data));
                setPieGraph(response.data);
            },
            (error) => {
                const _content =
                    (error.response && error.response.data) ||
                    error.message ||
                    error.toString();
                    console.log('error');
                    console.log(_content);
                    setPieGraph(_content);
            }
        );
        // --------------------
        BaseService.HttpGet("graph/showReceiptGraph001").then(
            (response) => {
                console.log('response');
                console.log(JSON.stringify(response.data));
                setDoughnut(response.data);
            },
            (error) => {
                const _content =
                    (error.response && error.response.data) ||
                    error.message ||
                    error.toString();
                    console.log('error');
                    console.log(_content);
                    setDoughnut(_content);
            }
        );
        // --------------------
        BaseService.HttpGet("graph/showReceiptGraph002").then(
            (response) => {
                //console.log('response');
                //console.log(JSON.stringify(response.data));
                setBar(response.data);
            },
            (error) => {
                const _content =
                    (error.response && error.response.data) ||
                    error.message ||
                    error.toString();
                    console.log('error');
                    console.log(_content);
                    setBar(_content);

                    console.log(_content.status);
                    if(_content.status == 401){
                        //window.location.reload();
                    }
                    

            }
        );
        // --------------------
        BaseService.HttpGet("graph/selectTransHistoryGraph001").then(
            (response) => {
                //console.log('response');
                //console.log(JSON.stringify(response.data));
                setPolar(response.data);
            },
            (error) => {
                const _content =
                    (error.response && error.response.data) ||
                    error.message ||
                    error.toString();
                    console.log('error');
                    console.log(_content);
                    setPolar(_content);

                    console.log(_content.status);
                    if(_content.status == 401){
                        //window.location.reload();
                    }
                    

            }
        );
         // --------------------
    }, []);


    const applyLightTheme = () => {
        const lineOptions = {
            plugins: {
                legend: {
                    labels: {
                        color: '#495057'
                    }
                }
            },
            scales: {
                x: {
                    ticks: {
                        color: '#495057'
                    },
                    grid: {
                        color: '#ebedef',
                    }
                },
                y: {
                    ticks: {
                        color: '#495057'
                    },
                    grid: {
                        color: '#ebedef',
                    }
                },
            }
        };
        const pieOptions = {
            plugins: {
                legend: {
                    labels: {
                        color: '#495057'
                    }
                }
            }
        };

        const barOptions = {
            plugins: {
                legend: {
                    labels: {
                        color: '#495057'
                    }
                }
            },
            scales: {
                x: {
                    ticks: {
                        color: '#495057'
                    },
                    grid: {
                        color: '#ebedef',
                    }
                },
                y: {
                    ticks: {
                        color: '#495057'
                    },
                    grid: {
                        color: '#ebedef',
                    }
                },
            }
        };

        setLineOptions(lineOptions)
        setPieOptions(pieOptions)
        setBarOptions(barOptions)

    }

    const applyDarkTheme = () => {
        const lineOptions = {
            plugins: {
                legend: {
                    labels: {
                        color: '#ebedef'
                    }
                }
            },
            scales: {
                x: {
                    ticks: {
                        color: '#ebedef'
                    },
                    grid: {
                        color: 'rgba(160, 167, 181, .3)',
                    }
                },
                y: {
                    ticks: {
                        color: '#ebedef'
                    },
                    grid: {
                        color: 'rgba(160, 167, 181, .3)',
                    }
                },
            }
        };
        const pieOptions = {
            plugins: {
                legend: {
                    labels: {
                        color: '#ebedef'
                    }
                }
            }
        };
        const barOptions = {
            plugins: {
                legend: {
                    labels: {
                        color: '#ebedef'
                    }
                }
            },
            scales: {
                x: {
                    ticks: {
                        color: '#ebedef'
                    },
                    grid: {
                        color: 'rgba(160, 167, 181, .3)',
                    }
                },
                y: {
                    ticks: {
                        color: '#ebedef'
                    },
                    grid: {
                        color: 'rgba(160, 167, 181, .3)',
                    }
                },
            }
        };

        setLineOptions(lineOptions)
        setPieOptions(pieOptions)
        setBarOptions(barOptions)
    }

    useEffect(() => {
        if (props.colorMode === 'light') {
            applyLightTheme();
        } else {
            applyDarkTheme();
        }
    }, [props.colorMode]);

    return (
        <div className="grid p-fluid">

            <div className="col-12 lg:col-6">
                <div className="card">
                    <h5>Linear Chart :Install Ticket Data</h5>
                    <Chart type="line" data={lineGraph} options={lineOptions} />
                </div>

                <div className="card">
                    <h5>Bar Chart :Receipt Table Data</h5>
                    <Chart type="bar" data={bar} options={barOptions} />
                </div>

                <div className="card flex flex-column align-items-center">
                    <h5>Radar Chart :Receipt  Data</h5>
                    <Chart type="radar" data={doughnut} options={pieOptions} style={{ width: '50%' }} />
                </div>
            </div>

            <div className="col-12 lg:col-6">
                <div className="card flex flex-column align-items-center">
                    <h5>Pie Chart :Install Ticket Data</h5>
                    <Chart type="pie" data={pieGraph} options={pieOptions} style={{ width: '50%' }} />
                </div>

                <div className="card flex flex-column align-items-center">
                    <h5>Doughnut Chart :Receipt Table Data</h5>
                    <Chart type="doughnut" data={doughnut} options={pieOptions} style={{ width: '50%' }} />
                </div>

                <div className="card flex flex-column align-items-center">
                    <h5>Polar Chart :Transfer History Data</h5>
                    <Chart type="polarArea" data={polar} options={pieOptions} style={{ width: '50%' }} />
                </div>
            </div>

        </div>
    )
}
 // eslint-disable-next-line
const comparisonFn = function (prevProps, nextProps) {
    return (prevProps.location.pathname === nextProps.location.pathname) && (prevProps.colorMode === nextProps.colorMode);
};

export default React.memo(ChartDemo, comparisonFn);
