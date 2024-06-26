function cargarDetallesLugar(codigo) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "./consulta_verDetalles?codigo=" + codigo, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
               var response = JSON.parse(xhr.responseText);
               var ubicacion = response.ubicacion;
               var detallesHtml = `
                    <h2>DETALLES DEL LUGAR</h2>
                    <h4>${response.nombre}</h4>
                    <img src="/imagenes/${response.imagen}" alt="${response.imagen}">
                    <p><strong>Código:</strong> <span>${response.codigo}</span></p>
                    <p><strong>Descripción:</strong> <span>${response.descripcion}</span></p>
                    <p><strong>Categoría:</strong> <span>${response.categoria}</span></p>
                    <p><strong>Días abiertos:</strong> <span>${response.dias_horario}</span></p>
                    <p><strong>Horario de apertura:</strong> <span>${response.hora_apertura}</span></p>
                    <p><strong>Horario de cierre:</strong> <span>${response.hora_cierre}</span></p>
                    <p><strong>Precio de la entrada:</strong> <span>${response.precio_entrada}</span></p>
                    <p><strong>Provincia:</strong> <span>${ubicacion.nombreProvincia}</span></p>
                    <p><strong>Cantón:</strong> <span>${ubicacion.canton}</span></p>
                    <p><strong>Distrito:</strong> <span>${ubicacion.distrito}</span></p>
                    <p><strong>Calidad de la recepción telefónica:</strong> <span>${response.calidad_recepcion_telefonica}</span></p>
                `;
                document.getElementById("verDetalles").innerHTML = detallesHtml;
                document.getElementById("verDetalles").classList.add("active");
            } else {
                console.error("Error al cargar los detalles del lugar. Estado de la solicitud:", xhr.status);
            }
        }
    };
    xhr.onerror = function() {
        console.error("Error de red al cargar los detalles del lugar.");
    };
    xhr.send();
}


function cargarDetallesArticulo(id) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET","./verDetallesArticulo?idArticulo=" + id, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
               var response = JSON.parse(xhr.responseText);
               var detallesHtml = `
                <h2>DETALLES DEL ARTÍCULO</h2>
                <p><strong>Identificador:</strong> <span>${response.identificador}</span></p>
                <p><strong>Título:</strong> <span>${response.titulo}</span></p>
                <p><strong>Tema:</strong> <span>${response.tema}</span></p>
                <p><strong>Descripción:</strong> <span>${response.descripcion}</span></p>
                <p><strong>Nombre del Autor:</strong> <span>${response.nombreAutor}</span></p>
                <p><strong>Fecha:</strong> <span>${response.fechaFormateada}</span></p>
                <p><strong>Acerca del Autor:</strong> <span>${response.acercaDelAutor}</span></p>
                <p><strong>Texto del Artículo:</strong> <span>${response.textoArticulo}</span></p>
                `;
                document.getElementById("verDetalles").innerHTML = detallesHtml;
                document.getElementById("verDetalles").classList.add("active");
            } else {
                console.error("Error al cargar los detalles del articulo. Estado de la solicitud:", xhr.status);
            }
        }
    };
    xhr.onerror = function() {
        console.error("Error de red al cargar los detalles del articulo.");
    };
    xhr.send();
}

function cargarDetallesEventoTuristico(id) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "./verDetallesEventoTuristico?id=" + id, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var eventoTuristico = JSON.parse(xhr.responseText);
                var detallesHtml = `
                    <h2>DETALLES DEL EVENTO TURÍSTICO</h2>
                    <p><strong>Identificador:</strong> <span>${eventoTuristico.codigo}</span></p>
                    <p><strong>Nombre del Evento:</strong> <span>${eventoTuristico.nombreEvento}</span></p>
                    <p><strong>Descripción:</strong> <span>${eventoTuristico.descripcion}</span></p>
                    <p><strong>Fecha:</strong> <span>${eventoTuristico.fechaFormateada}</span></p>
                    <p><strong>Nombre del Lugar:</strong> <span>${eventoTuristico.lugar.nombre}</span></p>
                    <p><strong>Título:</strong> <span>${eventoTuristico.titulo}</span></p>
                    <p><strong>Nombre del Autor:</strong> <span>${eventoTuristico.nombreAutor}</span></p>
                    <p><strong>Hora Inicial:</strong> <span>${eventoTuristico.horaInicial}</span></p>
                    <p><strong>Hora Final:</strong> <span>${eventoTuristico.horaFinal}</span></p>
                `;
                document.getElementById("verDetalles").innerHTML = detallesHtml;
                document.getElementById("verDetalles").classList.add("active");
            } else {
                console.error("Error al cargar los detalles del evento turístico. Estado de la solicitud:", xhr.status);
            }
        }
    };
    xhr.onerror = function() {
        console.error("Error de red al cargar los detalles del evento turístico.");
    };
    xhr.send();
}


function cargarDetallesColaborador(ide) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "./verDetallesColaborador?ide=" + ide, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
               var response = JSON.parse(xhr.responseText);
               var detallesHtml = `
                    <h2>DETALLES DEL LUGAR</h2>
                    <h4>${response.nombreEmpresa}</h4>

                    <p><strong>Identificador:</strong> <span>${response.ide}</span></p>
                    <p><strong>Nombre de la empresa:</strong> <span>${response.nombreEmpresa}</span></p>
                    <p><strong>Descripcion de la Empresa:</strong> <span>${response.descripcionEmpresa}</span></p>
                    <p><strong>Telefono de la empresa:</strong> <span>${response.direccionEmpresa}</span></p>
                    <p><strong>Sitio Web:</strong> <span>${response.telefonoEmpresa}</span></p>
                    <p><strong>Estado de aprobacion:</strong> <span>${response.sitioWeb}</span></p>
                    <p><strong>Tipo colaborador:</strong> <span>${response.estadoAprobacion}</span></p>
                    <p><strong>Redes Sociales:</strong> <span>${response.tipoColaborador}</span></p>
                    <p><strong>Cantón:</strong> <span>${response.redesSociales}</span></p>
                  `;
                document.getElementById("verDetalles").innerHTML = detallesHtml;
                document.getElementById("verDetalles").classList.add("active");
            } else {
                console.error("Error al cargar los detalles del Colaborador. Estado de la solicitud:", xhr.status);
            }
        }
    };
    xhr.onerror = function() {
        console.error("Error de red al cargar los detalles del lugar.");
    };
    xhr.send();
}

function cargarDetallesComentarioLugar(codigo) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "./verDetalles?codigo=" + codigo, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
               var response = JSON.parse(xhr.responseText);
               var detallesHtml = `
                    <h2>DETALLES DEL COMENTARIO</h2>
                    <p><span>${response.contenido}</span></p>
                    <p><strong>Código:</strong> <span>${response.codigo}</span></p>
                    <p><strong>Usuario:</strong> <span>${response.nombreUsuario}</span></p>
                    <p><strong>Fecha:</strong> <span>${response.fecha}</span></p>
                    <p><strong>Likes:</strong> <span>${response.cantidadLikes}</span></p>
                    <p><strong>Dislikes:</strong> <span>${response.cantidadDislikes}</span></p>
                    <p><strong>Etiquetas:</strong> <span>${response.etiquetas}</span></p>
                `;
                document.getElementById("verDetalles").innerHTML = detallesHtml;
                document.getElementById("verDetalles").classList.add("active");
            } else {
                console.error("Error al cargar los detalles del comentario. Estado de la solicitud:", xhr.status);
            }
        }
    };
    xhr.onerror = function() {
        console.error("Error de red al cargar los detalles del comentario.");
    };
    xhr.send();
}

function cargarDetallesComentarioArticulo(codigo) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "./verDetalles?codigo=" + codigo, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
               var response = JSON.parse(xhr.responseText);
               var detallesHtml = `
                    <h2>DETALLES DEL COMENTARIO</h2>
                    <p><span>${response.contenido}</span></p>
                    <p><strong>Código:</strong> <span>${response.codigo}</span></p>
                    <p><strong>Usuario:</strong> <span>${response.nombreUsuario}</span></p>
                    <p><strong>Fecha:</strong> <span>${response.fecha}</span></p>
                    <p><strong>Likes:</strong> <span>${response.cantidadLikes}</span></p>
                    <p><strong>Dislikes:</strong> <span>${response.cantidadDislikes}</span></p>
                    <p><strong>Etiquetas:</strong> <span>${response.etiquetas}</span></p>
                `;
                document.getElementById("verDetalles").innerHTML = detallesHtml;
                document.getElementById("verDetalles").classList.add("active");
            } else {
                console.error("Error al cargar los detalles del comentario. Estado de la solicitud:", xhr.status);
            }
        }
    };
    xhr.onerror = function() {
        console.error("Error de red al cargar los detalles del comentario.");
    };
    xhr.send();
}

function cargarDetallesComentarioEventoTuristico(codigo) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "./verDetalles?codigo=" + codigo, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
               var response = JSON.parse(xhr.responseText);
               var detallesHtml = `
                    <h2>DETALLES DEL COMENTARIO</h2>
                    <p><span>${response.contenido}</span></p>
                    <p><strong>Código:</strong> <span>${response.codigo}</span></p>
                    <p><strong>Usuario:</strong> <span>${response.nombreUsuario}</span></p>
                    <p><strong>Fecha:</strong> <span>${response.fecha}</span></p>
                    <p><strong>Likes:</strong> <span>${response.cantidadLikes}</span></p>
                    <p><strong>Dislikes:</strong> <span>${response.cantidadDislikes}</span></p>
                    <p><strong>Etiquetas:</strong> <span>${response.etiquetas}</span></p>
                `;
                document.getElementById("verDetalles").innerHTML = detallesHtml;
                document.getElementById("verDetalles").classList.add("active");
            } else {
                console.error("Error al cargar los detalles del comentario. Estado de la solicitud:", xhr.status);
            }
        }
    };
    xhr.onerror = function() {
        console.error("Error de red al cargar los detalles del comentario.");
    };
    xhr.send();
}

function cargarDetallesClima(codigo) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "./verDetallesClima?codigo=" + codigo, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
               var response = JSON.parse(xhr.responseText);
               var detallesHtml = `
                    <h2>DETALLES DEL CLIMA</h2>

                    <p><strong>Codigo:</strong> <span>${response.codigo}</span></p>
                    <p><strong>Temperatura:</strong> <span>${response.temperatura}</span></p>
                    <p><strong>Tipo de Clima:</strong> <span>${response.tipoClima}</span></p>
                    <p><strong>Fecha:</strong> <span>${response.fecha}</span></p>
                    <p><strong>Unidad Centigrados:</strong> <span>${response.unidadC}</span></p>
                    <p><strong>IndiceUV:</strong> <span>${response.indiceUV}</span></p>
                    <p><strong>Porcentaje Humedad :</strong> <span>${response.porcentajeHumedad}</span></p>
                    
                  `;
                document.getElementById("verDetalles").innerHTML = detallesHtml;
                document.getElementById("verDetalles").classList.add("active");
            } else {
                console.error("Error al cargar los detalles del Clima. Estado de la solicitud:", xhr.status);
            }
        }
    };
    xhr.onerror = function() {
        console.error("Error de red al cargar los detalles del clima.");
    };
    xhr.send();
}

function cargarDetallesUsuario(per_id) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "./verDetallesUsuario?per_id=" + per_id, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                var detallesHtml = `
                    <h2>DETALLES DEL USUARIO</h2>
                    <p><strong>ID:</strong> <span>${response.per_id}</span></p>
                    <p><strong>Nombre:</strong> <span>${response.nombre}</span></p>
                    <p><strong>Apellido:</strong> <span>${response.apellido}</span></p>
                    <p><strong>Cédula:</strong> <span>${response.cedula}</span></p>
                    <p><strong>Nombre de Usuario:</strong> <span>${response.nombreUsuario}</span></p>
                    <p><strong>Tipo de Usuario:</strong> <span>${response.tipoUsuario}</span></p>
                    <p><strong>Correo:</strong> <span>${response.correo}</span></p>
                    <p><strong>Teléfono:</strong> <span>${response.telefono}</span></p>
                    <p><strong>Fecha de Nacimiento:</strong> <span>${response.fechaNacimiento}</span></p>
                `;
                document.getElementById("verDetalles").innerHTML = detallesHtml;
                document.getElementById("verDetalles").classList.add("active");
            } else {
                console.error("Error al cargar los detalles del usuario. Estado de la solicitud:", xhr.status);
            }
        }
    };
    xhr.onerror = function() {
        console.error("Error de red al cargar los detalles del usuario.");
    };
    xhr.send();
}
function cargarDetallesColaborador(ide) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "./verDetallesColaboradorEmpresarial?ide=" + ide, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
               var response = JSON.parse(xhr.responseText);
               var detallesHtml = `
                    <h2>DETALLES DEL COLABORADOR</h2>

                    <p><strong>Identificador:</strong> <span>${response.ide}</span></p>
                    <p><strong>Nombre de la Empresa:</strong> <span>${response.nombreEmpresa}</span></p>
                    <p><strong>Descripcion de la Empresa:</strong> <span>${response.descripcionEmpresa}</span></p>
                    <p><strong>Direccion de la empresa :</strong> <span>${response.direccionEmpresa}</span></p>
                    <p><strong>Telefono de la empresa:</strong> <span>${response.telefonoEmpresa}</span></p>
                    <p><strong>Sitio Web:</strong> <span>${response.sitioWeb}</span></p>
                    <p><strong>Estado Aprobacion :</strong> <span>${response.estadoAprobacion}</span></p>
                    <p><strong>Tipo Colaborador :</strong> <span>${response.tipoColaborador}</span></p>
                    <p><strong>Redes Sociales:</strong> <span>${response.redesSociales}</span></p>
                
                  `;
                document.getElementById("verDetalles").innerHTML = detallesHtml;
            } else {
                console.error("Error al cargar los detalles del Clima. Estado de la solicitud:", xhr.status);
            }
        }
    };
    xhr.onerror = function() {
        console.error("Error de red al cargar los detalles del clima.");
    };
    xhr.send();
}
function cargarDetallesClima(codigo) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "./verDetallesClima?codigo=" + codigo, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
               var response = JSON.parse(xhr.responseText);
               var detallesHtml = `
                    <h2>DETALLES DEL CLIMA</h2>

                    <p><strong>Codigo:</strong> <span>${response.codigo}</span></p>
                    <p><strong>Temperatura:</strong> <span>${response.temperatura}</span></p>
                    <p><strong>Tipo de Clima:</strong> <span>${response.tipoClima}</span></p>
                    <p><strong>Fecha:</strong> <span>${response.fecha}</span></p>
                    <p><strong>Unidad Centigrados:</strong> <span>${response.unidadC}</span></p>
                    <p><strong>IndiceUV:</strong> <span>${response.indiceUV}</span></p>
                    <p><strong>Porcentaje Humedad :</strong> <span>${response.porcentajeHumedad}</span></p>
                    
                  `;
                document.getElementById("verDetalles").innerHTML = detallesHtml;
            } else {
                console.error("Error al cargar los detalles del Clima. Estado de la solicitud:", xhr.status);
            }
        }
    };
    xhr.onerror = function() {
        console.error("Error de red al cargar los detalles del clima.");
    };
    xhr.send();
}

function cargarDetallesRuta(id) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "./verDetallesRuta?id=" + id, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                var detallesHtml = `
                    <h2>DETALLES DE LA RUTA RECOMENDADA</h2>
                    <p><strong>Código:</strong> <span>${response.id}</span></p>
                    <p><strong>Comentario:</strong> <span>${response.comentario}</span></p>
                    <p><strong>Monto de Entradas (colones):</strong> <span>${response.montoEntradas}</span></p>
                    <p><strong>Puntuación de la ruta:</strong> <span>${response.puntuacion}</span></p>
                    <p><strong>Dificultad:</strong> <span>${response.dificultad}</span></p>
                    <p><strong>Tipo de ruta:</strong> <span>${response.tipo}</span></p>
                    <p><strong>Transporte recomendado:</strong> <span>${response.transporte}</span></p>
                    <p><strong>Cantidad de Lugares:</strong> <span>${response.cantidadLugares}</span></p>
                    <p><strong>Lugares:</strong> ${response.lugares.join(', ')}</p>
                `;
                document.getElementById("verDetalles").innerHTML = detallesHtml;
                document.getElementById("verDetalles").classList.add("active");
            } else {
                console.error("Error al cargar los detalles de la ruta. Estado de la solicitud:", xhr.status);
            }
        }
    };
    xhr.onerror = function() {
        console.error("Error de red al cargar los detalles de la ruta.");
    };
    xhr.send();
}
