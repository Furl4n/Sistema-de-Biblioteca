export interface Book{
    id: number,
    title: string,
    author: string,
    year: number,
    genre: string,
    status: string
}

export interface AddBookData{
    title: string,
    author: string,
    year: number,
    genre: string
}