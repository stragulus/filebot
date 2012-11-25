// filebot -script "fn:sysinfo"

// FileBot 2.62 (r993)
println net.sourceforge.filebot.Settings.applicationIdentifier

// JNA Native: 3.5.0
try {
	print 'JNA Native: '
	println com.sun.jna.Native.nativeVersion
} catch(error) {
	println error.cause
}

// MediaInfo: MediaInfoLib - v0.7.48
try {
	print 'MediaInfo: '
	println net.sourceforge.filebot.mediainfo.MediaInfo.version()
} catch(error) {
	println error.cause
}

// 7-Zip-JBinding: OK
try {
	print '7-Zip-JBinding: '
	net.sourceforge.filebot.archive.SevenZipLoader.requireNativeLibraries() // load 7-Zip-JBinding native libs
	println 'OK'
} catch(Throwable error) {
	println error
}

// Extended File Attributes
try {
	print 'Extended Attributes: '
	if (net.sourceforge.filebot.Settings.useExtendedFileAttributes()){
		// create new temp file
		def f = new File('.xattr-test')
		f.createNewFile() && f.deleteOnExit()
		
		// xattr write, read and verify
		def xattr = new net.sourceforge.filebot.media.MetaAttributes(f)
		def payload = new Date()
		xattr.setMetaData(payload)
		assert xattr.getMetaData() == payload
		println 'OK'
	} else {
		println 'DISABLED'
	}
} catch(Throwable error) {
	println error
}

// GIO and GVFS
try {
	if (net.sourceforge.filebot.gio.GVFS.supported) {
		print 'GVFS: '
		assert net.sourceforge.filebot.gio.GVFS.defaultVFS != null
		println 'OK'
	}
} catch(Throwable error) {
	println error
}

// Java(TM) SE Runtime Environment 1.6.0_30 (headless)
println net.sourceforge.filebot.Settings.javaRuntimeIdentifier

// 32-bit Java HotSpot(TM) Client VM
println String.format('%d-bit %s', com.sun.jna.Platform.is64Bit() ? 64 : 32, _system['java.vm.name'])

// Windows 7 (x86)
println String.format('%s (%s)', _system['os.name'], _system['os.arch'])
