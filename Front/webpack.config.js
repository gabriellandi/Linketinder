const path = require('path');
const CopyPlugin = require('copy-webpack-plugin')

module.exports = {
    mode: 'production',
    entry: {
        index: './src/main.ts',
        pageCadastroEmpresa: './src/Usuarios/Empresa/cadastroEmpresa.ts',
        pagePerfilEmpresa: './src/Usuarios/Empresa/perfilEmpresa.ts',
        pageCadastroCandidato: './src/Usuarios/Candidato/cadastroCandidato.ts',
        pageCadastroVaga: './src/Usuarios/Empresa/cadastraVagas.ts',
        pagePerfilCandidato: './src/Usuarios/Candidato/perfilCandidato.ts'
    },
    output: {
        filename: '[name].min.js',
        path: path.join(__dirname, 'dist')
    },
    
plugins: [
    new CopyPlugin({
        patterns: [{ from: "public", to: "", globOptions: { dot: true } }]
    })
],
    resolve: {
        extensions: [ '.ts', '.js' ]
    },
    module: {
        rules: [{
            test: /\.ts$/,
            use: 'ts-loader',
            exclude: /node_modules/
        }]
    }
}