document.addEventListener('input', e => {
    if (e.target.matches('.campo')) {
        const element = document.getElementById("documento");
        const defaultValue = document.getElementById("codigo").value;
        const operacion = document.getElementById("operacion");
        const form = document.getElementById("codigotransf");
        
        if (operacion.value.match(/transferencia/gi)) {
            form.classList.remove("hidden");
        } else {
            if (element.value !== defaultValue) element.value = defaultValue;
            form.classList.add("hidden");
        } 
    }
});