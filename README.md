# PDFJavaTest

¿Cómo harías para poder ejecutar N veces el proceso sobre el mismo directorio y solo modificar cada pdf una sola vez?

        ----- PRIMERA EJECUCIÓN -----
        
1. Primero haría una iteración a dicho directorio para comprobar cuáles ficheros coinciden con la extensión y el mime type de un archivo PDF.
2. Crearía un objeto llamado PDF o similar cuyos atributos sería ID, nombre del fichero y una variable booleana( Que nos tendrá una función de comprobar si el fichero cuenta con dicha característica o no para la validación a posteriori).
3. Crearía una lista temporal de todos los objetos PDF creados.
4. Con dicha lista, realizaría una iteración con una validación que compruebe los ficheros que tienen la última página en blanco. En caso afirmativo, se añadirían a una nueva lista definitiva que solo contienen ficheros con ese caso.
5. Compararía la lista definitiva con la temporal y almacenaría los casos que no existen en la definitiva en una tercera lista. Estos elementos no incluidos sería mandados a la función pertinente que añade la página en blanco en la última posición al fichero.
6. Añadiría los nuevos elementos de la tercera lista con la página en blanco pertinente a la segunda lista.
7. Almacenaría en un fichero JSON o en alguna Base de Datos este conjunto de objetos PDF.

        ----- N EJECUCIÓN -----
8. Recuperaría del elemento del almacenamiento la lista completa de los objetos PDF.
9. Comprobaría la longitud de dicha lista y la compararía con la cantidad de ficheros encontrados en el directorio.
10. Recuperaría los ficheros que no estaban almacenados y crearía objetos que serán situados en un conjunto pertinente.
11. Recorrería el conjunto y primero validaría con la función pertinente que si dicho fichero contiene o no la última página en blanco. En caso afirmativo, se añadiría a la lista de elementos recuperados de la base de datos.
12. En el caso de que no coincida, se utilizaría la función correspondiente que añade la página en blanco en el último lugar al PDF.
13. Una vez que todos los ficheros vuelven a contar con esta capacidad, se modificarían las variables booleanas a true y se volvería almacenar la lista en la base de datos, ahora contando con los nuevos elementos añadidos que no contaban con esta página.

        ----- CASOS A TENER EN CUENTA -----
        
1. Puede darse el caso de que se elimine una cantidad "X" de ficheros PDF y se añada esa misma cantidad al directorio. Esto caso daría que algunos ficheros se quedarían sin incluir con el proceso comentado. Para solucionar este caso, sería ideal crear un CRON con una serie de funcionalidades que comprobasen los ficheros del directorio que tienen y que no tienen páginas y los almacenase en la base de datos. A posteriori, cuando se ejecute la aplicación, se tendría modificar para que también se itere solo los elementos con la variable booleana en false.

2. Cambio de nombre. En el caso de cambio de nombre, se tendría que tener una nueva validación o un nuevo atributo en el objeto de JAVA que compruebe los mecanismos del fichero y poder identificar que ese objeto es el correcto. En caso de que ya existiese, se almacenaría en la base de datos con el nuevo nombre. En caso, es que se trata de un nuevo fichero. 

¿Qué pasa si el directorio contiene un fichero que no es un pdf?

Al tener una primera validación que comprueba el mime type y la extensión, ese fichero con diferente formato no sería tratado ya que la aplicación lo ignoraría en la iteración.

¿Cómo probar / ejecutar la aplicación?

La forma correcta sería mediante CRON si se va a utilizar en alguna aplicación real. Esto daría que siempre sabríamos que hay en ese fichero y si es correcto, además de no tener que parar y volver a arrancar la aplicación.
En el caso de este test, está realizado sobre un fichero main y sabiendo del problema comentado, me hubiera gustado haberlo realizado con los mecanismos mencionado.

¿Cómo podemos ver los logs?

Los logs que creemos con el framework pertinente, dependerá de la configuración que le hayamos dado y como lo queremos visualizar.
Por poner un ejemplo, si decidimos que sea almacenados en el servidor, habrá una carpeta en el MAVEN o TOMCAT que tenga dicha funcionalidad.
En el caso de que queremos almacenarlos en una base de datos para poder después crear una vista en donde el desarrollador pueda leer dichos ficheros con mayor facilidad y mejor desglosado.
