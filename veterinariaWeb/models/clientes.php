<?php
include 'conexion.php';

class Cliente extends Conexion {
    private $acceso;
    public function __CONSTRUCT(){
        $this->acceso = parent::getConexion();
    }

    public function login($dni = ''){
        try{
            $query = $this->acceso->prepare("SELECT * FROM clientes WHERE dni = ?");
            $query->execute(array($dni));
            return $query->fetch(PDO::FETCH_ASSOC);
        } catch (Exception $e){
            die($e->getMessage());
        }
    }

    public function agregarClientes($datos = []){
        try {
            $query = $this->acceso->prepare("CALL spu_clientes_registrar(?,?,?,?)");
            $query->execute(
                array(
                    $datos['nombres'],
                    $datos['apellidos'],
                    $datos['dni'],
                    $datos['claveacceso'],
                )
            );
        } catch (Exception $e){
            die($e->getMessage());
        }
    }

    public function listarClientes(){
        try {
            $query = $this->acceso->prepare("CALL spu_clientes_listar()");
            $query->execute();
            return $query->fetch(PDO::FETCH_ASSOC);
        } catch (Exception $e){
            die($e->getMessage());
        }
    }

    public function eliminarCliente($data = []){
        try{
            $query = $this->acceso->prepare("CALL spu_clientes_eliminar(?)");
            $query->execute(
                array(
                    $data['idcliente']
                )
            );
            return $query->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            die($e->getCode());
        }
    }

    public function obtenerCliente($data = []){
        try{
            $query = $this->acceso->prepare("CALL spu_clientes_obtenerDatos(?)");
            $query->execute(
                array(
                    $data['idcliente']
                )
            );
            return $query->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            die($e->getCode());
        }
    }

    public function actualizarCliente($data = []){
        try{
            $query = $this->acceso->prepare("CALL spu_cliente_actualizar(?, ?, ?, ?, ?)");
            $query->execute(
                array(
                    $data['idcliente'],
                    $data['nombres'],
                    $data['apellidos'],
                    $data['dni'],
                    $data['claveacceso'],
                )
            );
            return $query->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            die($e->getCode());
        }
    }
}
?>