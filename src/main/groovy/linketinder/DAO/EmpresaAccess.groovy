package linketinder.DAO

import linketinder.DAO.bancos.IConnect
import linketinder.DAO.bancos.EmpresaDAO

class EmpresaAccess extends Access{
    @Override
    protected IConnect createAccess() {
        return new EmpresaDAO()
    }
}
