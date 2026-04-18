import { api, BASE_URL } from "../service/api"
import type { AddLoan, Loan } from "../interfaces/Loan"
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";

const fetchData = async() => {
    return await api.get<Loan[]>(`${BASE_URL}/loan/get/user`);
}

export function useLoanData(){
    return useQuery({
        queryFn: () => fetchData(),
        queryKey: ['loans-data']
    })
}

const addLoan = async({ bookId, loanDate, dueDate, status }: AddLoan) =>{
    return await api.post("${BASE_URL}/loan/new", {
        bookId,
        loanDate,
        dueDate,
        status
    })
}

export function useAddLoan(){
    const QueryClient = useQueryClient();

    return useMutation({
        mutationFn: addLoan,

        onSuccess() {
            QueryClient.invalidateQueries({queryKey: ['loans-data'] })
        },
    })
}

const deleteLoan = async(reservationId: Number) => {
    return await api.delete(`${BASE_URL}/loan/delete/${reservationId}`)
}

export function useDeleteLoan(){
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: deleteLoan,

        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ['loans-data']})
        }
    })
}