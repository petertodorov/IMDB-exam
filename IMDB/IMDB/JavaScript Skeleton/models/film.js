const Sequelize = require('sequelize');

module.exports = function(sequelize){
    let Film = sequelize.define('Film',{
        name:{type:Sequelize.STRING,
            required:true,
            allowNull:false},
        genre:{type:Sequelize.STRING,
            required:true,
            allowNull:false},
        director:{type:Sequelize.STRING,
            required:true,
            allowNull:false},
        year:{type:Sequelize.INTEGER,
            required:true,
            allowNull:false},
    })
    return Film;
};