import { useParams } from 'react-router-dom'
import Loader from './Loader.tsx'
import { Alert, CardMedia, Fab } from '@mui/material'
import background from '../assets/img/background.png'
import { AuthHeader, Footer } from './AuthHeader.tsx'
import { useRefreshmentStandItem } from '../hooks/useRefreshmentStandItem.ts'
import DeleteIcon from '@mui/icons-material/Delete'

export default function RefreshmentStand() {
    const { id } = useParams()
    const { isLoading, isError, refreshmentStand, deleteRefreshmentStand } = useRefreshmentStandItem(id!)


    if (isLoading) {
        return <Loader />
    }

    if (isError || !refreshmentStand) {
        return <Alert severity='error'>Error</Alert>
    }

    return (
        <>
            <CardMedia sx={{ position: 'absolute', top: 0, bottom: 0, left: 0, right: 0, zIndex: -1 }} component='img'
                       image={background} alt='background' />
            <AuthHeader />
            <div style={{ display: 'flex', alignItems: 'center' }}>
                <div style={{ display: 'flex', flexDirection: 'column', padding: '200px', textAlign: 'center' }}>
                    <h1>{refreshmentStand.name}</h1>
                    <p>Status: {refreshmentStand.isOpen ? 'Open' : 'Closed'}</p>
                    <Fab>
                        <DeleteIcon onClick={deleteRefreshmentStand}/>
                    </Fab>
                </div>
                <img style={{ padding: '100px', height: '400px', width: '450px', borderRadius: '20%' }}
                     src={refreshmentStand.image} alt='refreshmentStand image' />
            </div>
            <Footer />
        </>
    )
}
