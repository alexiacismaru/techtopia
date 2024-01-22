import { useContext } from 'react'
import SecurityContext from '../context/SecurityContext.ts'
import { useQuery } from '@tanstack/react-query'
import { getAttractionByTags } from '../services/API.ts'

export function useTagsAttractions(tag: string) {
    const { isAuthenticated } = useContext(SecurityContext)

    const {
        isLoading: isDoingGet,
        isError: isErrorGet,
        data: tagAttractions,
    } = useQuery({
        queryKey: ['attractions', tag],
        queryFn: () => getAttractionByTags(tag),
        enabled: isAuthenticated(),
    })

    return {
        isLoadingTag: isDoingGet,
        isErrorTag: isErrorGet,
        attractions: tagAttractions || [],
    }
}
