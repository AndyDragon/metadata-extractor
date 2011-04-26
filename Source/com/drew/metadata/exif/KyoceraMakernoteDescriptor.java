/*
 * Copyright 2002-2011 Drew Noakes
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 * More information about this project is available at:
 *
 *    http://drewnoakes.com/code/exif/
 *    http://code.google.com/p/metadata-extractor/
 */
package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import com.drew.metadata.MetadataException;
import com.drew.metadata.TagDescriptor;

/**
 * Provides human-readable string representations of tag values stored in a <code>KyoceraMakernoteDirectory</code>.
 * <p/>
 * Some information about this makernote taken from here:
 * http://www.ozhiker.com/electronics/pjmt/jpeg_info/kyocera_mn.html
 * <p/>
 * Most manufacturer's MakerNote counts the "offset to data" from the first byte
 * of TIFF header (same as the other IFD), but Kyocera (along with Fujifilm) counts
 * it from the first byte of MakerNote itself.
 *
 * @author Drew Noakes http://drewnoakes.com
 */
public class KyoceraMakernoteDescriptor extends TagDescriptor
{
    public KyoceraMakernoteDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getDescription(int tagType) throws MetadataException
    {
        switch (tagType) {
            case KyoceraMakernoteDirectory.TAG_KYOCERA_PRINT_IMAGE_MATCHING_INFO:
                return getPrintImageMatchingInfoDescription();
            case KyoceraMakernoteDirectory.TAG_KYOCERA_PROPRIETARY_THUMBNAIL:
                return getProprietaryThumbnailDataDescription();
            default:
                return _directory.getString(tagType);
        }
    }

    public String getPrintImageMatchingInfoDescription() throws MetadataException
    {
        if (!_directory.containsTag(KyoceraMakernoteDirectory.TAG_KYOCERA_PRINT_IMAGE_MATCHING_INFO)) return null;
        byte[] bytes = _directory.getByteArray(KyoceraMakernoteDirectory.TAG_KYOCERA_PRINT_IMAGE_MATCHING_INFO);
        return "(" + bytes.length + " bytes)";
    }

    public String getProprietaryThumbnailDataDescription() throws MetadataException
    {
        if (!_directory.containsTag(KyoceraMakernoteDirectory.TAG_KYOCERA_PROPRIETARY_THUMBNAIL)) return null;
        byte[] bytes = _directory.getByteArray(KyoceraMakernoteDirectory.TAG_KYOCERA_PROPRIETARY_THUMBNAIL);
        return "(" + bytes.length + " bytes)";
    }
}
