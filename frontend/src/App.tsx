import './App.css'
import {BrowserRouter, Route, Routes} from 'react-router-dom'
import {QueryClient, QueryClientProvider} from '@tanstack/react-query'
import Techtopia from "./components/Techtopia.tsx";
import Attractions from "./components/Attractions.tsx";
import Attraction from "./components/Attraction.tsx";
import Map from "./components/Map.tsx";
import SecurityContextProvider from './context/SecurityContextProvider.tsx';
import AttractionsContextProvider from './context/AttractionContextProvider.tsx';
import { AuthHeader } from './components/AuthHeader.tsx';
import RouteGuard from './components/RouteGuard.tsx';
import RefreshmentStands from './components/RefreshmentStands.tsx'
import RefreshmentStand from './components/RefreshmentStand.tsx';

const queryClient = new QueryClient();

function App() {
    return (
            <QueryClientProvider client={queryClient}>
                <SecurityContextProvider>
                    <AttractionsContextProvider>
                        <AuthHeader />
                        <BrowserRouter>
                            <Routes>
                                <Route path="/techtopia" element={<RouteGuard component={<Techtopia />} />}/>
                                <Route path="/" element={<RouteGuard component={<Techtopia />} />}/>
                                <Route path="/attractions" element={<RouteGuard component={<Attractions />} />}/>
                                <Route path="/attractions/:id" element={<RouteGuard component={<Attraction />} />}/>
                                <Route path="/map" element={<RouteGuard component={<Map />} />}/>
                                <Route path="/refreshment-stands" element={<RouteGuard component={<RefreshmentStands />} />}/>
                                <Route path="/refreshment-stands/:id" element={<RouteGuard component={<RefreshmentStand />} />}/>
                            </Routes>
                        </BrowserRouter>
                    </AttractionsContextProvider>
                </SecurityContextProvider>
            </QueryClientProvider>
    )
}

export default App
