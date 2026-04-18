import { api, BASE_URL } from "../service/api";
import type { Book, AddBookData } from "../interfaces/Book";
import { useQuery, useQueryClient, useMutation } from '@tanstack/react-query';

const fetchData = async() => {
    return await api.get<Book[]>(`${BASE_URL}/book/get/all`);
}

export function useBookData(){
    return useQuery({
        queryFn: () => fetchData(),
        queryKey: ['books-data']
    })
}

const addBook = async({ title, author, year, genre }: AddBookData) =>{
    return await api.post("${BASE_URL}/book/new", {
        title:title,
        author: author,
        year: year,
        genre: genre
    })
}

export function useAddBook(){
    const QueryClient = useQueryClient();

    return useMutation({
        mutationFn: addBook,

        onSuccess() {
            QueryClient.invalidateQueries({queryKey: ['books-data'] })
        },
    })
}

const deleteBook = async(bookId: Number) => {
    return await api.delete(`${BASE_URL}/book/delete/${bookId}`)
}

export function useDeleteBook(){
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: deleteBook,

        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ['books-data']})
        }
    })
}