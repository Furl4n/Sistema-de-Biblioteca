import type { Book } from "./Book";

export interface Loan{
    id: number,
    book: Book,
    loanDate: string,
    dueDate: string,
    returnDate: string,
    status: string
}

export interface AddLoan{
    bookId: number,
    loanDate: string,
    dueDate: string,
    status: string
}