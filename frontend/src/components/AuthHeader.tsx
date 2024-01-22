import Button from '@mui/material/Button'
import { AppBar, Box, Container, Typography } from '@mui/material'
import { useContext } from 'react'
import SecurityContext from '../context/SecurityContext.ts'

export function Footer() {
    return (
        <Box
            sx={{
                display: 'block',
                position: 'fixed',
                width: '100%',
                bottom: 0,
                left: 0,
                zIndex: 'var(--header-and-footer)',
                backgroundColor: 'green',
            }}
            component='footer'
        >
            <Container>
                <Typography>
                    <p style={{color: 'white'}}>Copyright © Programming 6, 2023</p>
                </Typography>
            </Container>
        </Box>
    )
}

export function AuthHeader() {
    const { isAuthenticated, logout, loggedInUser } = useContext(SecurityContext)
    return (
        <header style={{ display: 'block', width: '100%', top: 0, left: 0, zIndex: 'var(--header-and-footer)' }}>
            <AppBar sx={{
                borderBottom: '1px solid white',
                boxShadow: '',
                backgroundColor: 'transparent',
                paddingLeft: '1rem',
                paddingRight: '1rem',
                marginLeft: 'auto',
                marginRight: 'auto',
            }}>
                <div
                    style={{
                        display: 'flex',
                        justifyContent: 'space-between',
                        padding: '5px',
                        alignItems: 'baseline',
                    }}
                >
                    <a href='/techtopia' style={{
                        fontSize: '40px', fontFamily: 'MAROLLA__', color: 'black',
                        fontWeight: 'bold',
                    }}>Techtopia</a>
                    <div style={{display: 'flex',
                        justifyContent: 'space-around',
                        padding: '5px',
                        alignItems: 'baseline',}}>
                        <a href='/refreshment-stands' style={{
                            marginRight: '10px', color: 'black'
                        }}>Refreshment stands</a>
                        <a href='/attractions' style={{ marginRight: '10px', color: 'white'
                        }}>Attractions</a>
                        <a href='/map' style={{ marginRight: '60px', color: 'white'
                        }}>Map</a>
                        {isAuthenticated() && (
                            <><p style={{marginRight: '10px'}}>Hello, {loggedInUser}</p><Button type='submit' variant='contained' sx={{ mt: 3, mb: 2, backgroundColor: 'green'}}
                                                                 onClick={logout}>
                                Log out
                            </Button ></>
                        )}
                    </div>
                </div>
            </AppBar>
        </header>
    )
}
