import { AuthHeader, Footer } from './AuthHeader.tsx'
import {CardMedia} from "@mui/material";
import index from '../assets/img/index.png'

export default function Techtopia() {
    return (
        <>
            <AuthHeader/>
            <div>
                <div style={{position: 'absolute', top: '33%', bottom: 0, left: 0, right: '50%'}}>
                    <h1 style={{color: 'black', fontSize: '50px'}}>Welcome to</h1>
                    <h1 style={{color: 'black', fontSize: '70px'}}>Techtopia</h1>
                </div>
                <CardMedia sx={{position: 'absolute', top: 0, bottom: 0, left: 0, right: 0, zIndex: -1}} component="img" image={index} alt="index" />
            </div>
            <Footer/>
        </>
    );
}
