import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query'
import { addRefreshmentStand, getRefreshmentStands } from '../services/API.ts'
import { RefreshmentStand } from '../model/POI.ts'
import { useContext } from 'react'
import SecurityContext from '../context/SecurityContext.ts'

export function useRefreshmentStands() {
    const { isAuthenticated } = useContext(SecurityContext)
    const queryClient = useQueryClient()
    const {
        isLoading: isDoingGet,
        isError: isErrorGet,
        data: refreshmentStands,
    } = useQuery({
        queryKey: ['refreshmentStands'],
        queryFn: () => getRefreshmentStands(),
        enabled: isAuthenticated(),
    })

    const {
        mutate,
        isLoading: isDoingPost,
        isError: isErrorPost,
    } = useMutation((item: Omit<RefreshmentStand, 'id'>) => addRefreshmentStand(item), {
        onSuccess: () => {
            queryClient.invalidateQueries(['refreshmentStands'])
        },
    });

    return {
        isLoading: isDoingGet || isDoingPost,
        isError: isErrorGet || isErrorPost,
        refreshmentStands: refreshmentStands || [],
        addRefreshmentStand: mutate
    }
}

