import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query'
import { deleteAttraction, getAttraction } from '../services/API.ts'
import { useContext } from 'react'
import SecurityContext from '../context/SecurityContext.ts'

export function useAttraction(attractionId: string) {
    const { isAuthenticated } = useContext(SecurityContext);
    const queryClient = useQueryClient();

    const {
        isLoading: isDoingGet,
        isError: isErrorGet,
        data: attraction,
    } = useQuery(['attraction', attractionId], () => getAttraction(attractionId), {
        enabled: isAuthenticated(),
    });

    const {
        mutate: deleteAttractionMutation,
        isLoading: isDoingDelete,
        isError: isErrorDelete,
    } = useMutation(
        (id: string) => deleteAttraction(id),
        {
            onSuccess: () => {
                queryClient.invalidateQueries(['attractions']);
            },
        }
    );

    return {
        isLoading: isDoingGet || isDoingDelete,
        isError: isErrorGet || isErrorDelete,
        attraction: attraction,
        deleteAttraction: deleteAttractionMutation
    };
}
