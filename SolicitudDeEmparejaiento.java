public class SolicitudDeEmparejaiento {
    private Usuario emisor;
    private Usuario receptor;
    private EstadoSolicitud estado;


    public void SolicitudEmparejamiento(Usuario emisor, Usuario receptor) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.estado = EstadoSolicitud.PENDIENTE;
    }

    public Usuario getEmisor() {
        return emisor;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public void getSolicitudesRecibidas() {
        return ;
    }


}

enum EstadoSolicitud {
    PENDIENTE,
    ACEPTADA,
    RECHAZADA,

}


