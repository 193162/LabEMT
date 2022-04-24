import React from "react";
import {useHistory} from "react-router-dom";

const bookEdit = (props) => {
    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "",
        author: "",
        availableCopies: 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.book.name;
        const availableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.book.availableCopies;
        const category = formData.category !== 0 ? formData.category : props.book.category.valueOf();
        const author = formData.author !== 0 ? formData.author : props.book.author.id;

        props.onEditProduct(props.book.id, name, category, author, availableCopies);
        history.push("/books");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder={props.book.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.author.map((term) => {
                                if(props.book.author !== undefined && props.book.author.id === term.id)
                                    <option selected={props.book.author.id} value={term.id}>{term.name}</option>
                                else return <option value={term.id}>{term.name}</option>

                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChange}>
                            {props.categories.map((term) => {
                                    if(props.book.author !== undefined && props.book.category.valueOf() === term.valueOf())
                                        <option selected={props.book.category.valueOf()} value={term.id}>{term.valueOf()}</option>
                                    else return <option value={term.valueOf()}>{term.valueOf()}</option>

                                }
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available Copies</label>
                        <input type="text"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder={props.book.availableCopies}
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default bookEdit;