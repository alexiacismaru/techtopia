import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query'
import { deleteRefreshmentStand, getRefreshmentStand } from '../services/API.ts'
import { useContext } from 'react'
import SecurityContext from '../context/SecurityContext.ts'

export function useRefreshmentStandItem(refreshmentStandId: string) {
    const { isAuthenticated } = useContext(SecurityContext)
    const queryClient = useQueryClient()

    const {
        isLoading: isDoingGet,
        isError: isErrorGet,
        data: refreshmentStand,
    } = useQuery({
        queryKey: ['refreshmentStand'],
        queryFn: () => getRefreshmentStand(refreshmentStandId),
        enabled: isAuthenticated(),
    })

    const deleteRefreshmentStandMutation = useMutation(() => deleteRefreshmentStand(refreshmentStandId), {
        onSuccess: () => {
            queryClient.invalidateQueries(['refreshmentStands']);
        },
    });

    const handleDeleteRefreshmentStand = () => {
        deleteRefreshmentStandMutation.mutate(); // Trigger the delete mutation
    };

    return {
        isLoading: isDoingGet || deleteRefreshmentStandMutation.isLoading,
        isError: isErrorGet || deleteRefreshmentStandMutation.isError,
        refreshmentStand,
        deleteRefreshmentStand: handleDeleteRefreshmentStand,
    };
}
