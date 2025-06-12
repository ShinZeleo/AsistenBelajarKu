package asistenbelajarku.service;

import asistenbelajarku.model.DataAplikasi;

public interface iPenyimpananService {
    void simpanSemuaData(DataAplikasi dataAplikasi);

    DataAplikasi muatSemuaData();
}