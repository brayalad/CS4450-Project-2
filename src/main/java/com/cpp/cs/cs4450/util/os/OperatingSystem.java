/***************************************************************
 * file: OperatingSystem.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Finds the type of Operating System program is running on.
 *
 ****************************************************************/

package com.cpp.cs.cs4450.util.os;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Constant Enumerations of Supported OperatingSystem.
 */
public enum OperatingSystem {
    WINDOWS("windows", "\\\\", Collections.unmodifiableSet(Stream.of("win").collect(Collectors.toSet())), ".dll"),
    MACOS("macosx","/", Collections.unmodifiableSet(Stream.of("mac").collect(Collectors.toSet())), ".dylib"),
    SOLARIS("solaris", "/", Collections.unmodifiableSet(Stream.of("sunos").collect(Collectors.toSet())), ".so"),
    LINUX("linux", "/", Collections.unmodifiableSet(Stream.of("nix", "nux", "nax").collect(Collectors.toSet())), ".so");

    /**
     * Name of the OS
     */
    private final String name;

    /**
     * The file path delimiter for the operating system.
     */
    private final String filePathDelimiter;

    /**
     * Names of the operating system
     */
    private final Set<String> osNames;

    /**
     * Extension for OS library files
     */
    private final String libraryFileExtension;

    /**
     * Constructor
     *
     * @param name name of OS
     * @param filePathDelimiter delimiter of files for operating system
     * @param osNames names the os goes by
     */
    OperatingSystem(final String name, final String filePathDelimiter, final Set<String> osNames, final String libraryFileExtension) {
        this.name = name;
        this.filePathDelimiter = filePathDelimiter;
        this.osNames = osNames;
        this.libraryFileExtension = libraryFileExtension;
    }

    /**
     * Gets the delimiter.
     *
     * @return {@link #filePathDelimiter}
     */
    public String getDelimiter() {
        return filePathDelimiter;
    }

    /**
     * Gets the Operating System names.
     *
     * @return {@link #osNames}
     */
    public Set<String> getOsNames() {
        return osNames;
    }

    /**
     * Checks if os of this type.
     *
     * @param os the os to check
     *
     * @return true if it is this type, false otherwise
     */
    public boolean isOsType(final String os){
        for(final String osName : osNames){
            if(os.contains(osName)){
                return true;
            }
        }

        return false;
    }

    /**
     * Gets OS name
     *
     * @return OS name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets library extension
     *
     * @return library file extension
     */
    public String getLibraryFileExtension() {
        return libraryFileExtension;
    }

    /**
     * Util class to help find the OS being used.
     */
    public static final class OperatingSystemUtils {

        /**
         * Error message for an unsupported operating systems.
         */
        private static final String UNSUPPORTED_OS_ERROR_MESSAGE_FORMAT = "[%s] is not supported by this program.";

        /**
         * Operating system name key for system properties
         */
        private static final String OPERATING_SYSTEM_NAME_PROPERTY_KEY = "os.name";

        /**
         * Current operating system name
         */
        private static final String CURRENT_OS_NAME = getOperatingSystemName();

        /**
         * Operating system being used.
         */
        private static OperatingSystem os = null;

        /**
         * Private Constructor
         */
        private OperatingSystemUtils(){}

        /**
         * Gets current operating system
         *
         * @return {@link #os}
         */
        public static OperatingSystem getOS(){
            if(os != null) return os;

            os = getSupportedOS();

            return os;
        }

        /**
         * Helper class to get OS
         *
         * @return  current operating system.
         */
        private static OperatingSystem getSupportedOS(){
            for(final OperatingSystem operatingSystem : OperatingSystem.values()){
                if(operatingSystem.isOsType(OperatingSystemUtils.CURRENT_OS_NAME)){
                    return operatingSystem;
                }
            }

            throw new RuntimeException(String.format(UNSUPPORTED_OS_ERROR_MESSAGE_FORMAT, CURRENT_OS_NAME));
        }

        /**
         * Helper function for getting operating system name.
         *
         * @return String format of operating system.
         */
        public static String getOperatingSystemName(){
            return System.getProperty(OPERATING_SYSTEM_NAME_PROPERTY_KEY).toLowerCase();
        }


    }

}