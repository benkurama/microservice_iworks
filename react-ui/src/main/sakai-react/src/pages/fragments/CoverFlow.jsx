import React, { useRef, useEffect, useState } from 'react';
import { Galleria } from 'primereact/galleria';
import { PhotoService } from '../../service/PhotoService';
import { Button } from 'primereact/button';
import { classNames } from 'primereact/utils';

import ReactEcharts from "echarts-for-react"; 
import {line001, line002, line003, line004} from '../../assets/json/lineChart';
import {bar001, bar002, bar003, bar004} from '../../assets/json/barChart';

import '../../assets/css/coverflow.css';

const CoverFlow = (props) =>{

    const [images, setImages] = useState(null);
    const [activeIndex, setActiveIndex] = useState(0);
    const [showThumbnails, setShowThumbnails] = useState(false);
    const [isAutoPlayActive, setAutoPlayActive] = useState(true);
    const [isFullScreen, setFullScreen] = useState(false);
    const galleriaService = new PhotoService();
    const galleria = useRef(null)

    const responsiveOptions = [
        {
            breakpoint: '1024px',
            numVisible: 5
        },
        {
            breakpoint: '960px',
            numVisible: 4
        },
        {
            breakpoint: '768px',
            numVisible: 3
        },
        {
            breakpoint: '560px',
            numVisible: 1
        }
    ];
    
    useEffect(() => {
    
        galleriaService.getImages02().then(data => setImages(data));
        bindDocumentListeners();

        return () => unbindDocumentListeners();
    }, []);

    useEffect(() => {
        //setAutoPlayActive(galleria.current.isAutoPlayActive())
        //galleria.current.stopSlideShow();
    },[isAutoPlayActive]);

    /*const onThumbnailChange = (event) => {
        setActiveIndex(event.index)
    }*/

    const onItemChange = (event) => {
        setActiveIndex(event.index)
    }

    const toggleFullScreen = () => {
        if (isFullScreen) {
            closeFullScreen();
        }
        else {
            openFullScreen();
        }
    }

    const onFullScreenChange = () => {
        setFullScreen(prevState => !prevState )
    }

    const openFullScreen = () => {
        let elem = document.querySelector('.custom-galleria');
        if (elem.requestFullscreen) {
            elem.requestFullscreen();
        }
        else if (elem.mozRequestFullScreen) { /* Firefox */
            elem.mozRequestFullScreen();
        }
        else if (elem.webkitRequestFullscreen) { /* Chrome, Safari & Opera */
            elem.webkitRequestFullscreen();
        }
        else if (elem.msRequestFullscreen) { /* IE/Edge */
            elem.msRequestFullscreen();
        }
    }

    const closeFullScreen = () => {
        if (document.exitFullscreen) {
            document.exitFullscreen();
        }
        else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        }
        else if (document.webkitExitFullscreen) {
            document.webkitExitFullscreen();
        }
        else if (document.msExitFullscreen) {
            document.msExitFullscreen();
        }
    }

    const bindDocumentListeners = () => {
        document.addEventListener("fullscreenchange", onFullScreenChange);
        document.addEventListener("mozfullscreenchange", onFullScreenChange);
        document.addEventListener("webkitfullscreenchange", onFullScreenChange);
        document.addEventListener("msfullscreenchange", onFullScreenChange);
    }

    const unbindDocumentListeners = () => {
        document.removeEventListener("fullscreenchange", onFullScreenChange);
        document.removeEventListener("mozfullscreenchange", onFullScreenChange);
        document.removeEventListener("webkitfullscreenchange", onFullScreenChange);
        document.removeEventListener("msfullscreenchange", onFullScreenChange);
    }

    const thumbnailTemplate = (item) => {

        const chartEntity2 = () => {
            switch(item.title) {
      
              case "Title 1":   return        <h4>line one</h4>;
              case "Title 2":   return        <h4>line two</h4>;
              case "Title 3":   return        <h4>line three</h4>;
              case "Title 4":   return        <h4>line four</h4>;

              case "Title 5":   return        <h4>bar one</h4>;
              case "Title 6":   return        <h4>line two</h4>;
              case "Title 7":   return        <h4>line three</h4>;
              case "Title 8":   return        <h4>line four</h4>;
    
              default:      return <h1>No chart match</h1>
            }
          }

        return (
            <div className="grid grid-nogutter justify-content-center" style={{display: 'block'}}>
               {chartEntity2()}
            </div>
        );
    }


    const itemTemplate = (item) => {

        const chartEntity = () => {
            switch(item.title) {
      
              case "Title 1":   return        <ReactEcharts option={props.param1} notMerge={true}  />;
              case "Title 2":   return        <ReactEcharts option={props.param2} notMerge={true}  />;
              case "Title 3":   return        <ReactEcharts option={props.param3} notMerge={true}   />;
              case "Title 4":   return        <ReactEcharts option={props.param4} notMerge={true}  />;

              case "Title 5":   return        <ReactEcharts option={bar001} notMerge={true} />;
              case "Title 6":   return        <ReactEcharts option={bar002} notMerge={true} />;
              case "Title 7":   return        <ReactEcharts option={bar003} notMerge={true} />;
              case "Title 8":   return        <ReactEcharts option={bar004} notMerge={true} />;
    
              default:      return <h1>No chart match</h1>
            }
          }


        if (isFullScreen) {
            return  (
                <div  style={{width:'600px', display: 'block'}}>
                    {chartEntity()} 
                    
                </div>
                
            )
        }

        return  (
            <div style={{ width: '100%', display: 'block', padding: '30px' }} className="bg_module">
                {chartEntity()} 
            </div>
            //<img src={`assets/${item.itemImageSrc}`} onError={(e) => e.target.src='https://www.primefaces.org/wp-content/uploads/2020/05/placeholder.png'} alt={item.alt} style={{ width: '100%', display: 'block' }} />
        )
       
    }



    const renderFooter = () => {
        let autoPlayClassName = classNames('pi', {
            'pi-play': !isAutoPlayActive,
            'pi-pause': isAutoPlayActive
        });

        let fullScreenClassName = classNames('pi', {
            'pi-window-maximize': !isFullScreen,
            'pi-window-minimize': isFullScreen
        });

        return (
            <div className="custom-galleria-footer">

                <Button icon="pi pi-list" onClick={() => setShowThumbnails(prevState => !prevState)} />
                <Button icon={autoPlayClassName} onClick={() => {
                    console.log('play on click');
                    if (!isAutoPlayActive) {
                        galleria.current.startSlideShow();
                        setAutoPlayActive(true)
                        console.log('play active true');
                    }
                    else {
                        galleria.current.stopSlideShow();
                        setAutoPlayActive(false)
                        console.log('play active false');
                    }
                }} />
                {
                    images && (
                        <span className="title-container">
                            <span>{activeIndex + 1}/{images.length}</span>
                            <span className="title">{images[activeIndex].title}</span>
                            <span>{images[activeIndex].alt}</span>
                        </span>
                    )
                }
                <Button icon={fullScreenClassName} onClick={() => toggleFullScreen()} className="fullscreen-button" />

                
            </div>
        );
    }

    const footer = renderFooter();
    const galleriaClassName = classNames('custom-galleria', {
        'fullscreen': isFullScreen
    });

    return (
        <div>
            <div className="galleria-demo">
                
                    <Galleria ref={galleria} value={images} activeIndex={activeIndex} onItemChange={onItemChange}
                        showThumbnails={showThumbnails} showItemNavigators showItemNavigatorsOnHover
                        numVisible={5} circular autoPlay transitionInterval={6000} responsiveOptions={responsiveOptions}
                        item={itemTemplate} thumbnail={thumbnailTemplate} footer={footer}
                        style={{ maxWidth: '640px' }} className={galleriaClassName} />
                
            </div>
        </div>
    );
}

export default CoverFlow;