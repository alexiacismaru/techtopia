import Loader from './Loader.tsx'
import { Alert, CardMedia } from '@mui/material'
import { useAttraction } from '../hooks/usePOIItems.ts'
import { useParams } from 'react-router-dom'
import background from '../assets/img/background.png'
import { Footer, AuthHeader } from './AuthHeader.tsx'
import { useTagsAttractions } from '../hooks/useTagsAttractions.ts'
import { POI } from '../model/POI.ts'

export default function Attraction() {
    const { id } = useParams()
    const { isLoading, isError, attraction } = useAttraction(id!)
    const { tag } = useParams()
    const { isLoadingTag, isErrorTag, attractions } = useTagsAttractions(tag!)

    if (isLoading || isLoadingTag) {
        return <Loader />
    }

    if (isError || !attraction || isErrorTag || !attractions) {
        return <Alert severity='error'>Error</Alert>
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
            <div style={{ display: 'flex', alignItems: 'center' }}>
                <div style={{ display: 'flex', flexDirection: 'column', padding: '200px' }}>
                    <h1>{attraction.name}</h1>
                    <h3>{attraction.description}</h3>
                    <p>Tags: {attraction.tags}</p>
                    <p>Age Group: {attraction.ageGroup}</p>
                </div>
                <img
                    style={{ padding: '100px', height: '400px', width: '450px', borderRadius: '20%' }}
                    src={attraction.image}
                    alt='attraction image'
                />
            </div>

            <section>
                <h2>Related Attractions</h2>
                <div style={{ display: 'flex', flexWrap: 'wrap' }}>
                    {attractions.map(({ name, image }: POI) => (
                        <div
                            style={{
                                display: 'flex',
                                justifyContent: 'space-between',
                                marginBottom: '10px',
                                alignItems: 'baseline',
                                borderBottom: '1px solid grey',
                                cursor: 'pointer',
                            }}
                        >
                            <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
                                <img
                                    src={image}
                                    alt={name}
                                    style={{ width: '150px', marginRight: '10px', height: '100px' }}
                                />
                                <h3>{name}</h3>
                            </div>
                        </div>
                    ))}
                </div>
            </section>
            <Footer />
        </>
    )
}
