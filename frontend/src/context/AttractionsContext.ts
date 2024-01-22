import { createContext } from 'react'
import { POI } from '../model/./POI.ts'

export interface AttractionsContext {
    attractions: POI[]
    currentAttractions: POI
    setCurrentAttractions: (attractions: POI) => void
}

export default createContext<AttractionsContext>({
    attractions: [],
    currentAttractions: {
        name: 'Context attractions',
        description: 'Context attractions',
        image: 'Context attractions',
        ageGroup: 'Context age group',
        tags: 'Context tags',
        id: '',
    },
    setCurrentAttractions: () => {},
})
