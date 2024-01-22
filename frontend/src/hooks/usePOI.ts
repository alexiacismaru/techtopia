import {useMutation, useQuery, useQueryClient} from '@tanstack/react-query'
import {POI} from "../model/./POI.ts";
import { addAttraction, getAttractions } from '../services/API.ts'
import SecurityContext from '../context/SecurityContext.ts'
import { useContext } from 'react'

export function useAttractions() {
    const { isAuthenticated } = useContext(SecurityContext)
    const queryClient = useQueryClient()
    const {
        isLoading: isDoingGet,
        isError: isErrorGet,
        data: attractions,
    } = useQuery({
        queryKey: ['attractions'],
        queryFn: () => getAttractions(),
        enabled: isAuthenticated(),
    })

    const {
        mutate,
        isLoading: isDoingPost,
        isError: isErrorPost,
    } = useMutation((item: Omit<POI, 'id'>) => addAttraction(item), {
        onSuccess: () => {
            queryClient.invalidateQueries(['attractions'])
        },
    });

    return {
        isLoading: isDoingGet || isDoingPost,
        isError: isErrorGet || isErrorPost,
        attractions: attractions || [],
        addAttraction: mutate
    }
}
