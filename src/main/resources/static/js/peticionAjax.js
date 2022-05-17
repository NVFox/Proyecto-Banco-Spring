$(document).ready(function () {

    const nombreTabla = document.querySelector(".legend-form")
            .id
            .replace("form-", "");

    const listaElementos = [].slice.call(document.querySelectorAll(".campo"));

    const formulario = document.getElementById("form-objeto");

    function convertirAObjeto(lista) {
        const objeto = {
            tabla: nombreTabla
        };
        lista.map((item, i) => objeto[`campo${i + 1}`] = item.name);

        return objeto;
    }

    function convertirObjetoValores(lista) {
        const objeto = {};
        lista.map(item => objeto[item.name] = item.value === "" ? null : `${item.value}`);

        return objeto;
    }

    function obtenerObjetoFinal(compareArr, data) {
        return data.map(item => {
            return Object.keys(item).reduce((acc, property) => {
                if (Object.values(compareArr).includes(property)) acc[property] = item[property]
                return acc
            }, {})
        })
    }

    $(".btn-consulta").click(function () {
        $.ajax({
            type: "GET",
            url: "clientes/tabla",
            success: function (data) {
                data = obtenerObjetoFinal(convertirAObjeto(listaElementos), data);
                document.querySelector("#tabla-js > thead > tr").innerHTML = "";
                document.querySelector("#tabla-js > tbody").innerHTML = "";

                Object.keys(data[0]).map(item => {
                    const newElement = document.createElement("th");
                    newElement.textContent = item.substring(0, 1).toUpperCase() + item.substring(1);
                    $("#tabla-js > thead > tr").append($(newElement));
                });

                data.map(i => {
                    const newRow = document.createElement("tr");

                    Object.values(i).map((item, x) => {
                        const newElement = document.createElement("td");

                        if ((Object.keys(i)[x]).match(/imagen/gi)) {
                            const img = document.createElement("img");
                            img.src = item;
                            img.style.width = "100px";

                            $(img).appendTo($(newElement));
                        } else {
                            newElement.textContent = item;
                        }
                        $(newElement).appendTo($(newRow));
                    });

                    $("#tabla-js > tbody").append($(newRow));
                });
            }
        });
    });
    
    function realizarPeticion(tipo, url) {
        console.log(convertirObjetoValores(listaElementos));

        formulario.method = tipo;
        formulario.action = formulario.action + url
        formulario.submit()
    }

    $(".btn-insertar").click(function () {
        realizarPeticion("POST", "registro");
    });
    
    $(".btn-actualizar").click(function () {
        realizarPeticion("POST", "actualizar")
    });
    
    $(".btn-eliminar").click(function() {
        const data = convertirObjetoValores(listaElementos);
        realizarPeticion("GET", `eliminar/${data[Object.keys(data)[0]]}`)
    });
});