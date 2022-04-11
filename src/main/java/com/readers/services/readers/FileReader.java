package com.readers.services.readers;

import com.readers.payload.SaleObjectPayload;

public interface FileReader {
	SaleObjectPayload read(String fileName);
}
