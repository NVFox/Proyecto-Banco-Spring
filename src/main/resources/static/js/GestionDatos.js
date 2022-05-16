const tabla = document.getElementById("tabla-alterna");
const tbody = document.createElement("tbody");

const consultarUsuario = async() => {
    const res = await fetch("ServletConsultaJS");
    const results = [...await res.json()];
    console.log(results);
    
    tabla.innerHTML = "";
    
    
    if (results.length > 0) {
        results.map((item, i) => {
            if (i === 0) tabla.innerHTML = `<thead>
                                            <tr>
                                            ${Object.keys(item).map(x => `<th>${x}</th>`)}
                                            </tr>
                                            </thead>`;
            tbody.innerHTML += `<tr>
            
</tr>`;
        });
    }
};

document.querySelector(".btn-consulta").addEventListener("click", consultarUsuario());