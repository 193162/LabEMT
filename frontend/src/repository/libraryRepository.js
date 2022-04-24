import axios from '../custom-axios/axios'

const LibraryService = {
    fetchBooks: () => {
        return axios.get("/books");
    },
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    fetchCountries: () => {
        return axios.get("/countries");
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (name, category, author, availableCopies) => {
        return axios.post("/books/add", {
            "name" : name,
            "category" : category,
            "author" : author,
            "availableCopies": availableCopies
        });
    },
    editBook: (id, name, category, author, availableCopies) => {
        return axios.put(`/products/edit/${id}`, {
            "name" : name,
            "category" : category,
            "author" : author,
            "availableCopies": availableCopies
        });
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    }
}

export default LibraryService;