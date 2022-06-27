import React, { useEffect, useState }  from 'react';
import { Carousel } from 'primereact/carousel';
//import { Button } from 'primereact/button';
import { ProductService } from '../../service/ProductService';
import { PhotoService } from '../../service/PhotoService';
import { Chart } from 'primereact/chart';
import { Galleria } from 'primereact/galleria';

const TestDemo = (props) => {

    const [products, setProducts] = useState([]);
    const [images, setImages] = useState([]);

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

    const galleriaResponsiveOptions = [
        {
            breakpoint: "1024px",
            numVisible: 5,
        },
        {
            breakpoint: "960px",
            numVisible: 4,
        },
        {
            breakpoint: "768px",
            numVisible: 3,
        },
        {
            breakpoint: "560px",
            numVisible: 1,
        },
    ];

    const barData = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [
            {
                label: 'My First dataset',
                backgroundColor: '#2f4860',
                data: [65, 59, 80, 81, 56, 55, 40]
            },
            {
                label: 'My Second dataset',
                backgroundColor: '#00bb7e',
                data: [28, 48, 40, 19, 86, 27, 90]
            }
        ]
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

    useEffect(() => {
        const productService = new ProductService();
        productService.getProductsSmall().then((products) => setProducts(products));

        const photoService = new PhotoService();
        photoService.getImages().then((images) => setImages(images));

    }, []);

    const galleriaItemTemplate = (item) => 
    {
        return (
            <div className="product-item">
                <div className="product-item-content">

                   
                    <Chart type="bar" data={barData} options={barOptions} />
                
                </div>
            </div>
        )
    }
    const galleriaThumbnailTemplate = (item) => <img src={`assets/${item.thumbnailImageSrc}`} alt={item.alt} style={{ width: '100%', display: 'block' }} />;

    const carouselItemTemplate = (product) => {
        return (
            <div className="product-item">
                <div className="product-item-content">

                    <div className="card">
                    <h5>Bar Chart</h5>
                    <Chart type="bar" data={barData} options={barOptions} />
                </div>
                </div>
            </div>
        );
    };

    return(

        <div className="grid p-fluid">

            <div className="col-6">    
                <div className="card">
                    <h5>Carousel</h5>
                    <Carousel autoplayInterval={5000} value={products} numVisible={1} numScroll={1}  orientation="vertical"
                        verticalViewPortHeight="340px" 
                        responsiveOptions={carouselResponsiveOptions} itemTemplate={carouselItemTemplate}>
                    </Carousel>
                </div>
            </div>


            <div className="col-12 lg:col-6">



                <div className="card">
                    <Galleria value={images} responsiveOptions={galleriaResponsiveOptions} numVisible={7} 
                            item={galleriaItemTemplate} thumbnail={galleriaThumbnailTemplate}
                            circular autoPlay transitionInterval={2000}
                            ></Galleria>
                </div>

            </div>

        </div>
    )

}

const comparisonFn = function (prevProps, nextProps) {
    return (prevProps.location.pathname === nextProps.location.pathname) && (prevProps.colorMode === nextProps.colorMode);
};

export default React.memo(TestDemo, comparisonFn);