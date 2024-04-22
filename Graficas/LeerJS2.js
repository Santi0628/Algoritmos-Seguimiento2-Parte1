const fs = require('fs');

function leerArchivoTxt(ruta) {
    // Leer el archivo de texto
    const datos = fs.readFileSync(ruta, 'utf-8');
    
    // Dividir los datos por líneas
    const lineas = datos.split('\n');
    
    // Inicializar las estructuras para almacenar los datos
    const estructura1 = [];
    const estructura2 = [];

    let estructuraActual = null; // Variable para rastrear en qué estructura estamos

    // Iterar sobre cada línea del archivo
    for (const linea of lineas) {
        // Si la línea está vacía, continuar con la siguiente línea
        if (linea.trim() === '') {
            continue;
        }
        
        // Si la línea contiene ':' al final, es un nuevo conjunto de datos
        if (linea.endsWith(':')) {
            const clave = linea.replace(':', '');
            if (clave === '40000000') {
                estructuraActual = estructura1;
            } else if (clave === '30000000') {
                estructuraActual = estructura2;
            }
            continue;
        }

        // Dividir la línea por ':' para obtener la clave y el valor
        const [clave, valor] = linea.split(':').map(item => item.trim());
        
        // Almacenar la clave y el valor en la estructura actual
        estructuraActual.push({ clave: clave, valor: parseFloat(valor) });
    }

    // Ordenar las estructuras por valor (orden descendente)
    estructura1.sort((a, b) => {
        if (a.valor !== b.valor) {
            return b.valor - a.valor;
        } else {
            // Si los valores son iguales, ordenar alfabéticamente por clave
            return a.clave.localeCompare(b.clave);
        }
    });

    estructura2.sort((a, b) => {
        if (a.valor !== b.valor) {
            return b.valor - a.valor;
        } else {
            // Si los valores son iguales, ordenar alfabéticamente por clave
            return a.clave.localeCompare(b.clave);
        }
    });

    return [estructura1, estructura2];
}

// Ruta del archivo
const ruta = '../resultados/resultadosOrdenados.txt';

// Llamar a la función para leer el archivo y obtener las estructuras
const [estructura1, estructura2] = leerArchivoTxt(ruta);

// Imprimir las estructuras
console.log('40000000:');
console.log(estructura1);
console.log('30000000:');
console.log(estructura2);
