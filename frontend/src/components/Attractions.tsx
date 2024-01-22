import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAttractions } from '../hooks/usePOI.ts'
import Loader from './Loader'
import { Alert, Card, CardMedia, Fab, TextField } from '@mui/material'
import { AuthHeader, Footer } from './AuthHeader.tsx'
import { AddAttractionDialog } from './AddAttractionDialog'
import background from '../assets/img/background.png'
import FilterListIcon from '@mui/icons-material/FilterList'
import MapIcon from '@mui/icons-material/Map'
import AddIcon from '@mui/icons-material/Add'
import SearchIcon from '@mui/icons-material/Search'
import { POI } from '../model/./POI.ts'

export default function Attractions() {
    const navigate = useNavigate()
    const { isLoading, isError, attractions, addAttraction } = useAttractions()
    const [isDialogOpen, setIsDialogOpen] = useState(false)
    const [isFilterOpen, setIsFilterOpen] = useState(false)
    const [tagsFilter, setTagsFilter] = useState('')
    const [ageGroupFilter, setAgeGroupFilter] = useState('')
    const [searchText, setSearchText] = useState('')


    if (isLoading) {
        return <Loader />
    }

    if (isError) {
        return <Alert severity='error'>Error</Alert>
    }

    const toggleFilter = () => {
        setIsFilterOpen(!isFilterOpen)
    }

    const filteredAttractions = attractions
        .filter((attraction: POI) =>
            attraction.ageGroup?.toLowerCase().includes(ageGroupFilter.toLowerCase()),
        )
        .filter((attraction: POI) =>
            attraction.tags?.toLowerCase().includes(tagsFilter.toLowerCase()),
        )

    return (
        <>
            <AuthHeader />
            <CardMedia
                sx={{
                    position: 'absolute',
                    top: 0,
                    bottom: 0,
                    left: 0,
                    right: 0,
                    zIndex: -1,
                }}
                component='img'
                image={background}
                alt='background'
            />
            <Fab
                size='large'
                sx={{
                    backgroundColor: 'white',
                    position: 'absolute',
                    bottom: '80%',
                    right: '10%',
                }}
                onClick={toggleFilter}
            >
                <FilterListIcon />
            </Fab>
            <Fab
                size='large'
                aria-label='add'
                sx={{
                    backgroundColor: 'white',
                    position: 'absolute',
                    bottom: '80%',
                    right: '5%',
                }}
                onClick={() => setIsDialogOpen(true)}
            >
                <AddIcon />
            </Fab>
            <a href='/map'>
                <Fab
                    size='large'
                    sx={{
                        backgroundColor: 'white',
                        position: 'absolute',
                        bottom: '80%',
                        right: '15%',
                    }}
                >
                    <MapIcon />
                </Fab>
            </a>
            <div
                style={{
                    marginTop: '115px',
                    display: 'flex',
                    justifyContent: 'space-around',
                    alignItems: 'baseline',
                    borderBottom: '2px solid black',
                }}
            >
                <SearchIcon />
                <input
                    type='text'
                    value={searchText}
                    onChange={(e) => setSearchText(e.target.value)}
                    style={{
                        width: '700px',
                        border: 'none',
                    }}
                />
            </div>
            <div>
                <AddAttractionDialog
                    isOpen={isDialogOpen}
                    onSubmit={(item) => {
                        addAttraction({ ...item})
                    }}
                    onClose={() => setIsDialogOpen(false)}
                />
                {isFilterOpen && (
                    <Card
                        sx={{
                            display: 'flex',
                            flexDirection: 'column',
                            padding: '20px',
                            position: 'absolute',
                            bottom: '65%',
                            right: '10%',
                        }}
                    >
                        <TextField
                            type='text'
                            placeholder='Filter by tags'
                            value={tagsFilter}
                            onChange={(e) => setTagsFilter(e.target.value)}
                            sx={{ marginBottom: '10px' }}
                        />
                        <TextField
                            type='text'
                            placeholder='Filter by age group'
                            value={ageGroupFilter}
                            onChange={(e) => setAgeGroupFilter(e.target.value)}
                        />
                    </Card>
                )}
                <div style={{ marginTop: '70px' }}>
                    {filteredAttractions
                        .filter((attraction: POI) =>
                            searchText.trim() === '' ||
                            attraction.name.toLowerCase().includes(searchText.toLowerCase()),
                        )
                        .map(({ id, name, image }: POI) => (
                            <div
                                style={{
                                    display: 'flex',
                                    justifyContent: 'space-between',
                                    marginBottom: '10px',
                                    alignItems: 'baseline',
                                    borderBottom: '1px solid grey',
                                    cursor: 'pointer',
                                }}
                                onClick={() => navigate(`/attractions/${id}`)}
                            >
                                <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between'}}>
                                    <img
                                        src={image}
                                        alt={name}
                                        style={{ width: '150px', marginRight: '10px' , height: '100px'}}
                                    />
                                    <h3>{name}</h3>
                                </div>
                            </div>
                        ))}
                </div>
            </div>
            <Footer />
        </>
    )
}
