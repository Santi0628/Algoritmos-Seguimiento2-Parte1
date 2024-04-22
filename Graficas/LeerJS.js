const fs = require('fs');

function leerArchivo(path) {
    try {
        const data = fs.readFileSync(path, 'utf8');
        const lineas = data.split('\n');
        const arreglo1 = [];
        const arreglo2 = [];
        let titulo = '';

        for (const linea of lineas) {
            if (linea.includes('>')) {
                // Si la línea contiene '>', entonces es un título
                titulo = linea.trim();
            } else {
                // Si no contiene '>', entonces es un número
                const numero = parseFloat(linea.trim());
                if (!isNaN(numero)) {
                    // Solo agregar el número si es un valor válido
                    if (titulo.includes('30000000')) {
                        arreglo1.push(numero);
                    } else if (titulo.includes('40000000')) {
                        arreglo2.push(numero);
                    }
                }
            }
        }

        return { arreglo1, arreglo2 };
    } catch (error) {
        console.error('Error al leer el archivo:', error);
        return { arreglo1: [], arreglo2: [] };
    }
}

// Uso del método
const path = './resultados/resultadosOrdenados.txt'; // Reemplaza con la ruta real de tu archivo
const { arreglo1, arreglo2 } = leerArchivo(path);

console.log('Arreglo 1 (30000000>):', arreglo1);
console.log('Arreglo 2 (40000000>):', arreglo2);
