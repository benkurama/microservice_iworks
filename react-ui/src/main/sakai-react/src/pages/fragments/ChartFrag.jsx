import React, { useState, useEffect, useRef } from 'react';

import ReactEcharts from "echarts-for-react"; 

import { Carousel } from 'primereact/carousel';
import { Toast } from 'primereact/toast';

import { ProductService } from '../../service/ProductService';

//import {line001, line002, line003, line004} from '../../assets/json/lineChart';


const carouselResponsiveOptions = [
    {
        breakpoint: "1024px",
        numVisible: 1,
        numScroll: 1,
    },
    {
        breakpoint: "768px",
        numVisible: 1,
        numScroll: 1,
    },
    {
        breakpoint: "560px",
        numVisible: 1,
        numScroll: 1,
    },
];


const ChartFrag001 = (props) => {

    const [products, setProducts] = useState([]);
    const carousel001 = useRef();
    const toast = useRef();

    useEffect(() => {
        const productService = new ProductService();
        productService.getDashboardDatas().then((products) => setProducts(products));
       
    }, []);


    const carouselItemTemplate = (product) => {
        const chartEntity = () => {
            switch(product.id) {
      
              case "1000":   return        <ReactEcharts option={props.param1} theme={""} />;
              case "2000":   return        <ReactEcharts option={props.param2} />;
              case "3000":   return        <ReactEcharts option={props.param3} />;
              case "4000":   return        <ReactEcharts option={props.param4} />;
    
              default:      return <h1>No chart match</h1>
            }
          }
      
          return (
            <div className="product-item">
                <div className="product-item-content" style={{width:'90%', margin:'auto'}}>
                    <div>{ chartEntity() }</div>
                </div>
            </div>
          )
    };


    return (
        <div>
            <Toast ref={toast} />
            
            <div className="bg_module"
                onMouseEnter={e=>{
                    //toast.current.show({severity: 'info', summary: 'onTouch', detail: ''});
                }}

                onMouseLeave={e=>{ 
                    //toast.current.show({severity: 'warn', summary: 'onLeave', detail: ''}); 
                }}

                >
                    <Carousel autoplayInterval={props.playInterval} value={products} numVisible={1} numScroll={1}  orientation="vertical"
                        verticalViewPortHeight="300px"
                        responsiveOptions={carouselResponsiveOptions} itemTemplate={carouselItemTemplate}
                        id="carousel001" ref={carousel001}
                        >
                    </Carousel>
                </div>


        </div>
    )
}

export default ChartFrag001;