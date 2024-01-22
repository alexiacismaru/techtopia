import { useState } from 'react'
import Loader from './Loader.tsx'
import { Alert, Card, CardMedia, Fab } from '@mui/material'
import { AuthHeader, Footer } from './AuthHeader.tsx'
import background from '../assets/img/background.png'
import FilterListIcon from '@mui/icons-material/FilterList'
import MapIcon from '@mui/icons-material/Map'
import SearchIcon from '@mui/icons-material/Search'
import { useRefreshmentStands } from '../hooks/useRefreshmentStands.ts'
import { RefreshmentStand } from '../model/POI.ts'
import { useNavigate } from 'react-router-dom'
import AddIcon from '@mui/icons-material/Add'
import { AddRefreshmentDialog } from './AddRefreshmentDialog.tsx'

export default function RefreshmentStands() {
    const navigate = useNavigate()
    const { isLoading, isError, refreshmentStands, addRefreshmentStand } = useRefreshmentStands();
    const [isDialogOpen, setIsDialogOpen] = useState(false)
    const [isFilterOpen, setIsFilterOpen] = useState(false);
    const [isOpenFilter, setIsOpenFilter] = useState('');
    const [searchText, setSearchText] = useState('');

    if (isLoading) {
        return <Loader />;
    }

    if (isError) {
        return <Alert severity="error">Error</Alert>;
    }

    const toggleFilter = () => {
        setIsFilterOpen(!isFilterOpen);
    };

    const filteredAttractions = refreshmentStands
        .filter((refreshmentStand: RefreshmentStand) =>
            isOpenFilter
                ? (isOpenFilter === 'open' && refreshmentStand.isOpen) ||
                (isOpenFilter === 'closed' && !refreshmentStand.isOpen)
                : true
        );

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
                component="img"
                image={background}
                alt="background"
            />
            <Fab
                size="large"
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
            <a href="/map">
                <Fab
                    size="large"
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
                    type="text"
                    value={searchText}
                    onChange={(e) => setSearchText(e.target.value)}
                    style={{
                        width: '700px',
                        border: 'none',
                    }}
                />
            </div>
            <div>
                <AddRefreshmentDialog
                    isOpen={isDialogOpen}
                    onSubmit={(item) => {
                        addRefreshmentStand({ ...item})
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
                            top: '21%',
                            right: '10%',
                        }}
                    >
                        <div>
                            <label>
                                <input
                                    type="radio"
                                    name="openClosedFilter"
                                    value=""
                                    checked={isOpenFilter === ''}
                                    onChange={() => setIsOpenFilter('')}
                                />
                                All
                            </label>
                            <label>
                                <input
                                    type="radio"
                                    name="openClosedFilter"
                                    value="open"
                                    checked={isOpenFilter === 'open'}
                                    onChange={() => setIsOpenFilter('open')}
                                />
                                Open
                            </label>
                            <label>
                                <input
                                    type="radio"
                                    name="openClosedFilter"
                                    value="closed"
                                    checked={isOpenFilter === 'closed'}
                                    onChange={() => setIsOpenFilter('closed')}
                                />
                                Closed
                            </label>
                        </div>
                    </Card>
                )}
                <div style={{ marginTop: '70px' }}>
                    {filteredAttractions
                        .filter((refreshmentStand: RefreshmentStand) =>
                            searchText.trim() === '' ||
                            refreshmentStand.name.toLowerCase().includes(searchText.toLowerCase())
                        )
                        .map(({ id, name, image }: RefreshmentStand) => (
                            <div
                                style={{
                                    display: 'flex',
                                    justifyContent: 'space-between',
                                    marginBottom: '10px',
                                    alignItems: 'baseline',
                                    borderBottom: '1px solid grey',
                                    cursor: 'pointer',
                                }}
                                onClick={() => navigate(`/refreshment-stands/${id}`)}
                            >
                                <div style={{ display: 'flex', alignItems: 'baseline' }}>
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
    );
}
