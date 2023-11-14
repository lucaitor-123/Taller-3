public class Usuario {

        private String nombreUsuario;
        private String contraseña;
        private String descripcion;
        private int edad;
        private boolean emparejado;
        private Usuario pareja;

        public Usuario(String nombreUsuario, String contraseña, String descripcion, int edad) {
            this.nombreUsuario = nombreUsuario;
            this.contraseña = contraseña;
            this.descripcion = descripcion;
            this.edad = edad;
            this.emparejado = emparejado;


        }

        public Usuario(String nombreUsuario, String contraseña, String descripcion, int edad, boolean emparejado) {
            this.nombreUsuario = nombreUsuario;
            this.contraseña = contraseña;
            this.descripcion = descripcion;
            this.edad = edad;
            this.emparejado = emparejado;
            this.pareja = null;
        }


        public String getNombreUsuario() {
            return nombreUsuario;
        }

        public void setNombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
        }

        public String getContraseña() {
            return contraseña;
        }

        public void setContraseña(String contraseña) {
            this.contraseña = contraseña;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public int getEdad() {
            return edad;
        }

        public void setEdad(int edad) {
            this.edad = edad;
        }

        public boolean isEmparejado() {
            return emparejado;
        }

        public void setEmparejado(boolean emparejado) {
            this.emparejado = emparejado;
        }



        public boolean getEmparejado() { return emparejado; }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Usuario usuario = (Usuario) o;
            return nombreUsuario.equals(usuario.nombreUsuario);
        }


        public void cancelarEmparejamiento() {
        }

        public void rechazarSolicitud(String nombreEmisor) {

        }

        public void add(Usuario usuario) {

        }
    }


