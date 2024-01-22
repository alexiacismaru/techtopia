import { ReactElement, useState } from 'react'
import SettingsContext from './AttractionsContext'
import { POI } from '../model/./POI.ts'
import { useAttractions } from '../hooks/usePOI.ts'
import { Alert } from '@mui/material'
import Loader from '../components/Loader.tsx'

interface WithChildren {
    children: ReactElement | ReactElement[]
}

export default function AttractionsContextProvider({ children }: WithChildren) {
    const [currentAttractions, setCurrentAttractions] = useState<POI | undefined>(undefined) // could be stored in local storage
    const { isLoading, isError, attractions } = useAttractions()

    if (isLoading) {
        return <Loader />
    }

    if (isError || !attractions || attractions.length === 0)
        return <Alert severity="error">Error loading attractions</Alert>

    if (!currentAttractions) {
        setCurrentAttractions(attractions[0])
        return <Loader />
    }

    return (
        <SettingsContext.Provider value={{ attractions, currentAttractions, setCurrentAttractions }}>
            {children}
        </SettingsContext.Provider>
    )
}
