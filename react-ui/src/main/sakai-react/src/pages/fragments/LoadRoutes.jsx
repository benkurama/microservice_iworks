import { Route, Routes } from 'react-router-dom';

import Dashboard from '../../pages/fragments/Dashboard';
import ButtonDemo from '../../components/ButtonDemo';
import ChartDemo from '../../components/ChartDemo';
import Documentation from '../../components/Documentation';
import FileDemo from '../../components/FileDemo';
import FloatLabelDemo from '../../components/FloatLabelDemo';
import FormLayoutDemo from '../../components/FormLayoutDemo';
import InputDemo from '../../components/InputDemo';
import ListDemo from '../../components/ListDemo';
import MenuDemo from '../../components/MenuDemo';
import MessagesDemo from '../../components/MessagesDemo';
import MiscDemo from '../../components/MiscDemo';
import OverlayDemo from '../../components/OverlayDemo';
import MediaDemo from '../../components/MediaDemo';
import PanelDemo from '../../components/PanelDemo';
import TableDemo from '../../components/TableDemo';
import TreeDemo from '../../components/TreeDemo';
import InvalidStateDemo from '../../components/InvalidStateDemo';
import BlocksDemo from '../../components/BlocksDemo';
import IconsDemo from '../../components/IconsDemo';

import Graphs from '../../pages/fragments/Graphs';
import TestDemo from '../../pages/fragments/Test';

import Crud from '../Crud';
import EmptyPage from '../EmptyPage';
import TimelineDemo from '../TimelineDemo';

const LoadRoutes = (props) =>{

    return (
        <Routes>
            <Route path="/" element={<Dashboard colorMode={props.colorMode} location={props.location} />} /> 
            <Route path="/formlayout" element={<FormLayoutDemo />} />
            <Route path="/input" element={<InputDemo />} />
            <Route path="/floatlabel" element={<FloatLabelDemo/>} />
            <Route path="/invalidstate" element={<InvalidStateDemo/>} />
            <Route path="/button" element={<ButtonDemo/>} />
            <Route path="/table" element={<TableDemo/>} />
            <Route path="/list" element={<ListDemo/>} />
            <Route path="/tree" element={<TreeDemo/>} />
            <Route path="/panel" element={<PanelDemo/>} />
            <Route path="/overlay" element={<OverlayDemo/>} />
            <Route path="/media" element={<MediaDemo/>} />
            <Route path="/menu" element={<MenuDemo /> } />
            <Route path="/messages" element={<MessagesDemo/>} />
            <Route path="/blocks" element={<BlocksDemo/>} />
            <Route path="/icons" element={<IconsDemo/>} />
            <Route path="/file" component={<FileDemo/>} />
            <Route path="/chart" element ={ <ChartDemo colorMode={props.colorMode} location={props.location} />} />
            <Route path="/misc" element={<MiscDemo/>} />
            <Route path="/timeline" element={<TimelineDemo/>} />
            <Route path="/crud" element={<Crud/>} />
            <Route path="/empty" element={<EmptyPage/>} />
            <Route path="/documentation" element={<Documentation/>} />

            <Route path="/graphs" element ={ <Graphs colorMode={props.colorMode} location={props.location} />} />
            <Route path="/testdemo" element={<TestDemo colorMode={props.colorMode} location={props.location} />} />
            
        </Routes>

    )
}

export default LoadRoutes;