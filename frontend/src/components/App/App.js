import './App.css';
import React, {Component} from "react";
import {BrowserRouter as router, Redirect, Route, Router} from "react-router-dom"
import Books from '../Books/BookList/books';
import Authors from '../Authors/authors';
import Countries from '../Countries/countries';
import Header from '../Header/header';
import BookAdd from '../Books/BookAdd/bookAdd'
import BookEdit from '../Books/BookEdit/bookEdit'
import LibraryService from "../../repository/libraryRepository";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            books: [],
            authors: [],
            countries: [],
            selectedBooks: []
        }
    }

    render = () => {
        return (
            <Router>
                <Header/>
                <main>
                    <div className={"container"}>
                        <Route path={"/books/add"} exact renter={() => <BookAdd authors={this.state.authors} category={this.state.category} onAddBook={this.addBook}/>}/>
                        <Route path={"/books/edit/:id"} exact renter={() => <BookEdit authors={this.state.authors} category={this.state.category} onEditBook={this.editBook} book={this.state.selectedBook}/>}/>
                        <Route path={"/books"} exact render={() => <Books books={this.state.books} onDelete={this.deleteBook} onEdit={this.getBook}/>}/>
                        <Route path={"/authors"} exact render={() => <Authors authors={this.state.authors}/>}/>
                        <Route path={"/countries"} exact render={() => <Countries countries={this.state.countries}/>}/>
                        <Redirect to={"/books"}/>
                    </div>

                </main>
            </Router>


        );
    }


    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data//,
                    //authors: data.data
                })
            });
    }

    loadAuthors = () => {
        LibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data//,
                    //countries: data.data
                })
            });
    }

    loadCountries = () => {
        LibraryService.fetchCountries()
            .then((data) => {
                this.setState({
                    countries: data.data
                })
            });
    }

    deleteBook = (id) => {
        LibraryService.deleteBook(id).then(() => {
            this.loadBooks();
        })
    }

    addBook = (name, category, author, availableCopies) => {
        LibraryService.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            })
    }

    componentDidMount() {
        this.loadBooks();
        this.loadAuthors();
        this.loadCountries();
    }

    getBook = (id) => {
        LibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    editBook = (id, name, category, author, availabeCopies) => {
        LibraryService.editBook(id, name, category, author, availabeCopies)
            .then(() => {
                this.loadBooks();
            });
    }
}

export default App;
