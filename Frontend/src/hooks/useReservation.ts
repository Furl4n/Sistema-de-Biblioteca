import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query"
import type { addReservation, Reservation } from "../interfaces/Reservation"
import { api, BASE_URL } from "../service/api"

const fetchData = async() => {
    return await api.get<Reservation[]>(`${BASE_URL}/reservation/get/user`)
}

export function useReservationData(){
    return useQuery({
        queryFn: fetchData,
        queryKey: ['reservations-data']
    })
}

const addReservation = async({ bookId, reservationDate, expirationDate, status }: addReservation) => {
    return await api.post(`${BASE_URL}/reservation/new`, {
        bookId,
        reservationDate,
        expirationDate,
        status
    })
}

export function useAddReservation(){
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: addReservation,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['reservations-data']})
        }
    })
}

const deleteReservation = async(reservationId: Number) => {
    return await api.delete(`${BASE_URL}/reservation/delete/${reservationId}`)
}

export function useDeleteReservation(){
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: deleteReservation,

        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ['reservations-data']})
        }
    })
}