import React, { useEffect, useState } from 'react';
import { Chart } from 'primereact/chart';
import BaseService from '../../service/BaseService';


const ChartDemo = (props) => {
    const [lineOptions, setLineOptions] = useState(null)
    const [pieOptions, setPieOptions] = useState(null)

    const [lineGraph, setLineGraph] = useState(null)
    const [pieGraph, setPieGraph] = useState(null)

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

        setLineOptions(lineOptions)
        setPieOptions(pieOptions)

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
        

        setLineOptions(lineOptions)
        setPieOptions(pieOptions)

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
            </div>
            <div className="col-12 lg:col-6">
                
            <div className="card flex flex-column align-items-center">
                    <h5>Pie Chart :Install Ticket Data</h5>
                    <Chart type="pie" data={pieGraph} options={pieOptions} style={{ width: '50%' }} />
                </div>
                
            </div>
        </div>
    )
}

const comparisonFn = function (prevProps, nextProps) {
    return (prevProps.location.pathname === nextProps.location.pathname) && (prevProps.colorMode === nextProps.colorMode);
};

export default React.memo(ChartDemo, comparisonFn);
