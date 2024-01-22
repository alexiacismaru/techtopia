import React, { useState } from 'react'
import { AuthHeader, Footer } from './AuthHeader.tsx'
import background from '../assets/img/background.png'
import { Card, CardMedia, Fab } from '@mui/material'
import techtopia from '../assets/img/techtopia.jpg'
import LocationOnIcon from '@mui/icons-material/LocationOn'

export default function MapView() {
    const [showRefreshmentStands, setShowRefreshmentStands] = useState(true)
    const [showAttractions, setShowAttractions] = useState(true)

    const handleFilterChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, checked } = event.target

        if (name === 'refreshment-stands') {
            setShowRefreshmentStands(checked)
        } else if (name === 'attractions') {
            setShowAttractions(checked)
        }
    }

    return (
        <>
            <CardMedia
                sx={{ position: 'absolute', top: 0, bottom: 0, left: 0, right: 0, zIndex: -1 }}
                component='img'
                image={background}
                alt='background'
            />
            <AuthHeader />
            <CardMedia sx={{ position: 'relative' }} component='img' image={techtopia} alt='techtopia' />
            <Card sx={{
                position: 'absolute',
                bottom: '80%',
                right: '10%',
                display: 'flex',
                flexDirection: 'column',
                textAlign: 'right',
                padding: '10px'
            }}>
                <label>
                    <input
                        type='checkbox'
                        name='refreshment-stands'
                        checked={showRefreshmentStands}
                        onChange={handleFilterChange}
                    />
                    Refreshment Stands
                </label>

                <label>
                    <input
                        type='checkbox'
                        name='attractions'
                        checked={showAttractions}
                        onChange={handleFilterChange}
                    />
                    Attractions
                </label>
            </Card>

            {showRefreshmentStands && (
                <>
                    <a href='/refreshment-stands/1'>
                        <Fab
                            size='medium'
                            aria-label='add'
                            sx={{ backgroundColor: 'white', position: 'absolute', bottom: '65%', right: '45%' }}
                        >
                            <LocationOnIcon sx={{ position: 'absolute', color: 'red', fontSize: '30px' }} />
                        </Fab>
                    </a>
                    <a href='/refreshment-stands/2'>
                        <Fab
                            size='medium'
                            aria-label='add'
                            sx={{ backgroundColor: 'white', position: 'absolute', bottom: '47%', right: '43%' }}
                        >
                            <LocationOnIcon
                                sx={{ position: 'absolute', color: 'red', fontSize: '30px' }} />
                        </Fab>
                    </a>
                    <a href='/refreshment-stands/3'>
                        <Fab
                            size='medium'
                            aria-label='add'
                            sx={{ backgroundColor: 'white', position: 'absolute', bottom: '54%', right: '35%' }}
                        >
                            <LocationOnIcon
                                sx={{ position: 'absolute', color: 'red', fontSize: '30px' }} />
                        </Fab>
                    </a>
                    <a href='/refreshment-stands/4'>
                        <Fab
                            size='medium'
                            aria-label='add'
                            sx={{ backgroundColor: 'white', position: 'absolute', bottom: '27%', right: '58%' }}
                        >
                            <LocationOnIcon
                                sx={{ position: 'absolute', color: 'red', fontSize: '30px' }} />
                        </Fab>
                    </a>
                    <a href='/refreshment-stands/5'>
                        <Fab
                            size='medium'
                            aria-label='add'
                            sx={{ backgroundColor: 'white', position: 'absolute', bottom: '63%', right: '28%' }}
                        >
                            <LocationOnIcon
                                sx={{ position: 'absolute', color: 'red', fontSize: '30px' }} />
                        </Fab>
                    </a>
                    <a href='/refreshment-stands/6'>
                        <Fab
                            size='medium'
                            aria-label='add'
                            sx={{ backgroundColor: 'white', position: 'absolute', bottom: '50%', right: '47%' }}
                        >
                            <LocationOnIcon
                                sx={{ position: 'absolute', color: 'red', fontSize: '30px' }} />
                        </Fab>
                    </a>
                    <Footer />
                </>
                )}

            {showAttractions && (
                <>
                    <a href='/attractions/1'>
                        <Fab
                            size='medium'
                            aria-label='add'
                            sx={{ backgroundColor: 'white', position: 'absolute', bottom: '75%', right: '33%' }}
                        >
                            <LocationOnIcon sx={{ position: 'absolute', color: 'red', fontSize: '30px' }} />
                        </Fab>
                    </a>
                    <a href='/attractions/2'>
                        <Fab
                            size='medium'
                            aria-label='add'
                            sx={{ backgroundColor: 'white', position: 'absolute', bottom: '67%', right: '53%' }}
                        >
                            <LocationOnIcon
                                sx={{ position: 'absolute', color: 'red', fontSize: '30px' }} />
                        </Fab>
                    </a>
                    <a href='/attractions/3'>
                        <Fab
                            size='medium'
                            aria-label='add'
                            sx={{ backgroundColor: 'white', position: 'absolute', bottom: '60%', right: '60%' }}
                        >
                            <LocationOnIcon
                                sx={{ position: 'absolute', color: 'red', fontSize: '30px' }} />
                        </Fab>
                    </a>
                    <a href='/attractions/4'>
                        <Fab
                            size='medium'
                            aria-label='add'
                            sx={{ backgroundColor: 'white', position: 'absolute', bottom: '45%', right: '63%' }}
                        >
                            <LocationOnIcon
                                sx={{ position: 'absolute', color: 'red', fontSize: '30px' }} />
                        </Fab>
                    </a>
                    <a href='/attractions/5'>
                        <Fab
                            size='medium'
                            aria-label='add'
                            sx={{ backgroundColor: 'white', position: 'absolute', bottom: '33%', right: '65%' }}
                        >
                            <LocationOnIcon
                                sx={{ position: 'absolute', color: 'red', fontSize: '30px' }} />
                        </Fab>
                    </a>
                    <a href='/attractions/6'>
                        <Fab
                            size='medium'
                            aria-label='add'
                            sx={{ backgroundColor: 'white', position: 'absolute', bottom: '40%', right: '53%' }}
                        >
                            <LocationOnIcon
                                sx={{ position: 'absolute', color: 'red', fontSize: '30px' }} />
                        </Fab>
                    </a>
                    <a href='/attractions/7'>
                        <Fab
                            size='medium'
                            aria-label='add'
                            sx={{ backgroundColor: 'white', position: 'absolute', bottom: '65%', right: '40%' }}
                        >
                            <LocationOnIcon
                                sx={{ position: 'absolute', color: 'red', fontSize: '30px' }} />
                        </Fab>
                    </a>
                </>
            )}

            <Footer />
        </>
    )
}
