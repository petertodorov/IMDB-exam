const Film = require('../models').Film;

module.exports = {
    index: (req, res) => {
        Film.findAll().then(allFilms => {
            res.render('film/index', {'films': allFilms})
        });
    },

    createGet: (req, res) => {
        res.render('film/create')
    },

    createPost: (req, res) => {
        let filmDetails = req.body;
        Film.create(filmDetails).then(() => {
            res.redirect('/');
        });
    },

    editGet: (req, res) => {
        let id = req.params.id;
        Film.findById(id).then(found => {
            res.render('film/edit', found.dataValues)
        });
    },

    editPost: (req, res) => {
        let filmDetails = req.body;
        let id = req.params.id;
        Film.findById(id)
            .then(found =>found.update(filmDetails).then(()=>{res.redirect('/')}))
        },


    deleteGet: (req, res) => {
        let id = req.params.id;
        Film.findById(id).then(found => {
            res.render('film/delete', found.dataValues)
        });
    },
    deletePost: (req, res) => {
        let id = req.params.id;
        Film.findById(id).then(found => {found.destroy().then(()=>{res.redirect('/')})
        });
    }
};